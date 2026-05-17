import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.kotlin.dsl.the
import java.util.Properties

val localProperties = Properties().apply {
    rootProject.file("local.properties")
        .takeIf { it.exists() }
        ?.inputStream()
        ?.use(::load)
}

val baseUrl = localProperties.getProperty("AI_BASE_URL")
    ?: "http://127.0.0.1:20128/v1/"
val apiKey = localProperties.getProperty("AI_SERVICE_API_KEY")
    ?: "sk-e69a732c4bc1a76a-77h8et-7d631c07"

plugins {
    id("codebase.android.library")
    id("codebase.android.hilt")
}

val deps = the<LibrariesForLibs>()

android {
    namespace = "com.genesys.core.network"

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        buildConfigField("String", "BASE_URL", "\"$baseUrl\"")
        buildConfigField("String", "API_KEY", "\"$apiKey\"")
    }
}

dependencies {
    implementation(project(":core:model"))

    // OkHttp
    implementation(platform(deps.okhttpBom))
    implementation(deps.okhttp)
    implementation(deps.loggingInterceptor)

    // Retrofit
    implementation(deps.retrofit)
    implementation(deps.retrofit2KotlinCoroutinesAdapter)
    implementation(deps.converterGson)

    // Sandwich
    implementation(deps.sandwich)
    implementation(deps.sandwichRetrofit)

    // Gson
    implementation(deps.gson)
}
