import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.kotlin.dsl.the

plugins {
    id("codebase.android.feature")
}

val deps = the<LibrariesForLibs>()

android {
    namespace = "com.genesys.feature.notebook"
}

dependencies {
    implementation(deps.androidxLifecycleRuntimeCompose)
}
