plugins {
    id("codebase.android.feature")
}

android {
    namespace = "net.timelegend.chaka.viewer"

    defaultConfig {
        buildConfigField("String", "VERSION_NAME", "\"1.0\"")
    }

    sourceSets {
        getByName("main") {
            java.srcDir(project(":core:document-reader").projectDir.resolve("src/main/java"))
        }
    }
}

dependencies {
    implementation(project(":core:document-reader"))
    implementation(project(":core:ui"))
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation("androidx.recyclerview:recyclerview:1.3.2")
}
