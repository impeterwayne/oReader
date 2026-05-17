import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.kotlin.dsl.the

plugins {
    id("codebase.android.feature")
    alias(libs.plugins.kotlinSerialization)
}

val deps = the<LibrariesForLibs>()

android {
    namespace = "com.genesys.feature.library"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:model"))
    implementation(project(":core:domain"))
    implementation(project(":core:data"))
    implementation(project(":feature:koreader"))

    // AndroidX & Compose
    implementation(deps.androidxCoreKtx)
    implementation(platform(deps.composeBom))
    implementation(deps.composeFoundation)
    implementation(deps.composeUi)
    implementation(deps.composeUiGraphics)
    implementation(deps.composeUiToolingPreview)
    implementation(deps.activityCompose)
    implementation(deps.androidxLifecycleRuntimeCompose)
    implementation("androidx.compose.material:material")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.documentfile:documentfile:1.0.1")
    implementation(deps.xxpermissionKtx)

    // Navigation
    implementation(project(":core:navigation"))
    implementation(deps.navigation3Runtime)
    implementation(deps.navigation3Ui)

    // Hilt
    implementation(deps.hiltAndroid)
    ksp(deps.hiltCompiler)
    
    // Timber
    implementation(deps.timber)

    implementation(deps.orbitCompose)
}
