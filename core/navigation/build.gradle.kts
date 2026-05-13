plugins {
    id("codebase.android.library")
    id("codebase.android.compose")
    alias(libs.plugins.kotlinSerialization)
}

android {
    namespace = "com.genesys.core.navigation"
}

dependencies {
    implementation(libs.navigation3Runtime)
    implementation(libs.navigation3Ui)
}
