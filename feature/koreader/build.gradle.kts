import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.Sync
import org.gradle.api.tasks.TaskAction
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.the
import java.io.File
import java.net.URI
import java.security.MessageDigest

abstract class DownloadKoreaderRuntimeTask : DefaultTask() {
    @get:Input
    abstract val url: Property<String>

    @get:Input
    abstract val sha256: Property<String>

    @get:OutputFile
    abstract val outputFile: RegularFileProperty

    @TaskAction
    fun download() {
        val target = outputFile.get().asFile
        val expectedSha = sha256.get().lowercase()
        val skipChecksum = expectedSha.isEmpty()

        target.parentFile.mkdirs()
        if (target.exists()) {
            if (skipChecksum || target.sha256() == expectedSha) {
                logger.lifecycle("Using cached KOReader runtime APK: ${target.name}")
                return
            }
        }

        val partial = File(target.parentFile, "${target.name}.part")
        if (partial.exists()) {
            partial.delete()
        }

        logger.lifecycle("Downloading KOReader runtime APK from ${url.get()}")
        URI.create(url.get()).toURL().openStream().use { input ->
            partial.outputStream().use { output ->
                input.copyTo(output)
            }
        }

        if (!skipChecksum) {
            val actualSha = partial.sha256()
            check(actualSha == expectedSha) {
                "Downloaded KOReader runtime checksum mismatch. Expected $expectedSha, got $actualSha."
            }
        } else {
            logger.warn("Skipping checksum verification for ${target.name} - no checksum provided")
        }

        partial.copyTo(target, overwrite = true)
        partial.delete()
    }

    private fun File.sha256(): String {
        val digest = MessageDigest.getInstance("SHA-256")
        inputStream().buffered().use { input ->
            val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
            var read = input.read(buffer)
            while (read >= 0) {
                if (read > 0) {
                    digest.update(buffer, 0, read)
                }
                read = input.read(buffer)
            }
        }
        return digest.digest().joinToString(separator = "") { byte -> "%02x".format(byte) }
    }
}

plugins {
    id("codebase.android.library")
    id("codebase.android.hilt")
}

val deps = the<LibrariesForLibs>()

// Use the official Android release artifact as the embedded runtime bundle
// until the full native build is reproducible in this workspace.
val koreaderReleaseTag = "v2026.03"

// KOReader runtime artifacts for different ABIs
// Note: KOReader only provides arm, arm64, and x86 builds
data class KoreaderArtifact(
    val abi: String,
    val arch: String,
    val sha256: String?
)

val koreaderArtifacts = listOf(
    KoreaderArtifact("arm64-v8a", "arm64", "aa8c1f8330a2bddd65b8725dbb8dd9f86c6485eae4bfe17dc1160a31641a3ba8"),
    KoreaderArtifact("armeabi-v7a", "arm", null), // TODO: Add SHA256 checksum
    KoreaderArtifact("x86", "x86", null) // TODO: Add SHA256 checksum
)

val embeddedAssetsDir = layout.buildDirectory.dir("generated/embeddedKoreader/main/assets")
val embeddedJniLibsDir = layout.buildDirectory.dir("generated/embeddedKoreader/main/jniLibs")

// Download tasks for each ABI
val downloadTasks = koreaderArtifacts.map { artifact ->
    val artifactName = "koreader-android-${artifact.arch}-$koreaderReleaseTag.apk"
    val artifactUrl = "https://github.com/koreader/koreader/releases/download/$koreaderReleaseTag/$artifactName"
    val outputFile = layout.buildDirectory.file("embeddedKoreader/downloads/$artifactName")

    tasks.register<DownloadKoreaderRuntimeTask>("downloadKoreader${artifact.abi.replace("-", "")}") {
        url.set(artifactUrl)
        sha256.set(artifact.sha256 ?: "")
        outputFile.set(outputFile)
    }
}

// Extract assets from the first APK only (assets are the same across all ABIs)
val extractKoreaderRuntimeAssets = tasks.register<Sync>("extractKoreaderRuntimeAssets") {
    dependsOn(downloadTasks.first())
    into(embeddedAssetsDir)
    includeEmptyDirs = false

    from({
        zipTree(downloadTasks.first().get().outputFile.get().asFile)
    }) {
        include("assets/**")
        eachFile {
            path = path.removePrefix("assets/")
        }
    }
}

// Extract native libraries from all APKs
val extractKoreaderRuntimeJniLibs = tasks.register<Sync>("extractKoreaderRuntimeJniLibs") {
    dependsOn(downloadTasks)
    into(embeddedJniLibsDir)
    includeEmptyDirs = false

    koreaderArtifacts.forEachIndexed { index, artifact ->
        from({
            zipTree(downloadTasks[index].get().outputFile.get().asFile)
        }) {
            include("lib/${artifact.abi}/**")
            eachFile {
                path = path.removePrefix("lib/")
            }
        }
    }
}

android {
    namespace = "org.koreader.launcher"

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }

    defaultConfig {
        ndk {
            // Support all ABIs provided by KOReader (arm, arm64, x86)
            abiFilters.addAll(listOf("arm64-v8a", "armeabi-v7a", "x86"))
        }

        buildConfigField("String", "APP_NAME", "\"KOReader\"")
        buildConfigField("String", "FLAVOR_CHANNEL", "\"embedded\"")
        buildConfigField("String", "HOST_APPLICATION_ID", "\"com.genesys.codebase\"")
        buildConfigField("boolean", "IN_APP_UPDATES", "false")
        buildConfigField("boolean", "SUPPORTS_RUNTIME_CHANGES", "false")
        buildConfigField("String", "SEVENZIP_LIB", "\"koreader-monolibtic\"")
    }

    androidResources {
        noCompress += "7z"
    }

    sourceSets {
        getByName("main") {
            java.srcDirs("src/main/java")
            res.srcDirs("src/main/res")
            assets.srcDirs("src/main/assets")
            assets.srcDir(embeddedAssetsDir)
            jniLibs.srcDirs("src/main/jniLibs")
            jniLibs.srcDir(embeddedJniLibsDir)
        }
    }
}

tasks.named("preBuild").configure {
    dependsOn(extractKoreaderRuntimeAssets)
    dependsOn(extractKoreaderRuntimeJniLibs)
}

dependencies {
    implementation(project(":core:common"))

    implementation(deps.androidxActivity)
    implementation(deps.androidxAppcompat)
    implementation(deps.androidxCoreKtx)

    implementation(deps.timber)
}
