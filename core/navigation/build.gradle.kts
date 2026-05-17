plugins {
    id("codebase.android.library")
    id("codebase.android.compose")
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.kotlinParcelize)
}

android {
    namespace = "com.genesys.core.navigation"
}

dependencies {
    implementation(libs.navigation3Runtime)
    implementation(libs.navigation3Ui)
}
