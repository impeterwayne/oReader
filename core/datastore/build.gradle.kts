import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.kotlin.dsl.the

plugins {
    id("codebase.android.library")
    id("codebase.android.hilt")
}

val deps = the<LibrariesForLibs>()

android {
    namespace = "com.genesys.core.datastore"
}

dependencies {
    implementation(project(":core:common"))

    // MMKV
    implementation(deps.mmkv)
}
