import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Sync
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.register
import java.io.File
import java.security.MessageDigest

/**
 * Convention plugin for the KOReader runtime host module.
 *
 * Configures:
 * - ABI filters (default: arm64-v8a only)
 * - JNI/native library packaging from vendored KOReader build outputs
 * - Asset packaging for KOReader Lua runtime and resources
 */
class AndroidKoreaderConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val koreaderSourceDir = rootProject.layout.projectDirectory.dir("vendor/koreader")
            val androidLuaAsset = rootProject.layout.projectDirectory.file(
                "vendor/android-luajit-launcher/assets/android.lua"
            )
            val generatedAssetsDir = layout.buildDirectory.dir("generated/koreaderAssets/main")

            val syncKoreaderAssets = tasks.register<Sync>("syncKoreaderAssets") {
                group = "build setup"
                description = "Sync vendored KOReader runtime assets into the Android assets tree."

                into(generatedAssetsDir)

                from(koreaderSourceDir) {
                    into("koreader")
                    exclude(".git", ".git/**", ".ci/**", ".circleci/**", ".github/**")
                    exclude("doc/**", "spec/**", "test/**")
                }

                from(androidLuaAsset) {
                    into("koreader")
                }

                doFirst {
                    check(koreaderSourceDir.asFile.exists()) {
                        "Missing vendored KOReader sources at ${koreaderSourceDir.asFile}"
                    }
                    check(androidLuaAsset.asFile.exists()) {
                        "Missing android.lua asset at ${androidLuaAsset.asFile}"
                    }
                }

                doLast {
                    val versionFile = generatedAssetsDir.get().file("koreader/version").asFile
                    versionFile.parentFile.mkdirs()
                    versionFile.writeText(
                        computeAssetFingerprint(
                            koreaderSourceDir.asFile,
                            androidLuaAsset.asFile,
                        )
                    )
                }
            }

            with(pluginManager) {
                apply("codebase.android.library")
            }

            extensions.configure<LibraryExtension> {
                defaultConfig {
                    // Default to arm64-v8a only; can be overridden via gradle property
                    if (project.hasProperty("KOREADER_ABI_FILTERS")) {
                        ndk {
                            val filters = (project.property("KOREADER_ABI_FILTERS") as String)
                                .split(",")
                                .map { it.trim() }
                            abiFilters.addAll(filters)
                        }
                    } else {
                        ndk {
                            abiFilters.addAll(listOf("arm64-v8a"))
                        }
                    }
                }

                sourceSets {
                    getByName("main") {
                        // Native libraries from KOReader build will be placed here
                        jniLibs.srcDirs("src/main/jniLibs")
                        // KOReader Lua assets (frontend, plugins, fonts, etc.)
                        assets.srcDirs("src/main/assets")
                        assets.srcDir(generatedAssetsDir)
                    }
                }
            }

            tasks.matching { task ->
                task.name == "preBuild" ||
                    (task.name.startsWith("merge") && task.name.endsWith("Assets"))
            }.configureEach {
                dependsOn(syncKoreaderAssets)
            }
        }
    }

    private fun computeAssetFingerprint(koreaderSourceDir: File, androidLuaAsset: File): String {
        val digest = MessageDigest.getInstance("SHA-256")

        koreaderSourceDir
            .walkTopDown()
            .filter(File::isFile)
            .filterNot { file ->
                val relativePath = file.relativeTo(koreaderSourceDir)
                    .path
                    .replace(File.separatorChar, '/')
                relativePath == ".git" ||
                    relativePath.startsWith(".git/") ||
                    relativePath.startsWith(".ci/") ||
                    relativePath.startsWith(".circleci/") ||
                    relativePath.startsWith(".github/") ||
                    relativePath.startsWith("doc/") ||
                    relativePath.startsWith("spec/") ||
                    relativePath.startsWith("test/")
            }
            .sortedBy { it.relativeTo(koreaderSourceDir).path }
            .forEach { file ->
                val relativePath = file.relativeTo(koreaderSourceDir)
                    .path
                    .replace(File.separatorChar, '/')
                digest.update(relativePath.toByteArray())
                digest.update(file.length().toString().toByteArray())
                digest.update(file.lastModified().toString().toByteArray())
            }

        digest.update("android.lua".toByteArray())
        digest.update(androidLuaAsset.length().toString().toByteArray())
        digest.update(androidLuaAsset.lastModified().toString().toByteArray())

        return digest.digest().joinToString(separator = "") { byte -> "%02x".format(byte) }
    }
}
