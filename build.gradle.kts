plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.kotlinJvm) apply false
    alias(libs.plugins.kotlinParcelize) apply false
    alias(libs.plugins.kotlinCompose) apply false
    alias(libs.plugins.hiltAndroid) apply false
    alias(libs.plugins.ksp) apply false
}

// Ensure implicit intermediate projects have a clean task to satisfy Android Studio
project(":feature") {
    tasks.register("clean", Delete::class) {
        delete(layout.buildDirectory)
    }
}
project(":core") {
    tasks.register("clean", Delete::class) {
        delete(layout.buildDirectory)
    }
}
