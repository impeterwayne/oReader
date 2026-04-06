import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.kotlin.dsl.the

plugins {
    id("codebase.android.library")
    id("codebase.android.hilt")
    id("org.jetbrains.kotlin.plugin.parcelize")
    id("com.google.devtools.ksp")
}

val deps = the<LibrariesForLibs>()

android {
    namespace = "com.genesys.core.database"

    defaultConfig {
        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
    }
}

dependencies {
    implementation(project(":core:model"))

    // Room
    implementation(deps.androidxRoomRuntime)
    ksp(deps.androidxRoomCompiler)
    implementation(deps.androidxRoomKtx)

    // Moshi (for type converters)
    implementation(deps.moshi)
    implementation(deps.moshiKotlin)

    // Stroke payload compatibility with Notable's SB1 + LZ4 encoding
    implementation("at.yawk.lz4:lz4-java:1.10.3")

    // Coroutines
    implementation(deps.kotlinxCoroutinesCore)
    implementation(deps.kotlinxCoroutinesAndroid)
}
