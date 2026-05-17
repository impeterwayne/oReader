import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.kotlin.dsl.the

plugins {
    id("codebase.android.library")
    id("codebase.android.hilt")
}

val deps = the<LibrariesForLibs>()

android {
    namespace = "com.genesys.core.data"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:domain"))
    implementation(project(":core:network"))
    implementation(project(":core:database"))
    implementation(project(":core:common"))

    // DocumentFile
    implementation("androidx.documentfile:documentfile:1.0.1")

    // Coroutines
    implementation(deps.kotlinxCoroutinesCore)
    implementation(deps.kotlinxCoroutinesAndroid)

    // Sandwich
    implementation(deps.sandwich)
    implementation(deps.sandwichRetrofit)

    // Gson
    implementation(deps.gson)

    // Timber
    implementation(deps.timber)

    testImplementation(deps.junit)
    testImplementation(deps.retrofit)
    testImplementation(deps.converterGson)
    testImplementation(deps.loggingInterceptor)
    testImplementation(deps.sandwichRetrofit)
}
