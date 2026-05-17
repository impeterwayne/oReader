import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.kotlin.dsl.the

plugins {
    id("codebase.android.library")
    id("org.jetbrains.kotlin.plugin.parcelize")
}

val deps = the<LibrariesForLibs>()

android {
    namespace = "com.genesys.core.model"
}

dependencies {
    // Gson annotations
    implementation(deps.gson)

    // AndroidX annotations
    implementation(deps.androidxCoreKtx)
}
