import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.kotlin.dsl.the

plugins {
    id("codebase.android.library")
}

val deps = the<LibrariesForLibs>()

android {
    namespace = "com.genesys.core.common"
}

dependencies {
    // Timber
    implementation(deps.timber)

    api(deps.lifecycleViewmodelKtx)
    api(deps.androidxCoreKtx)

    // Coroutines
    implementation(deps.kotlinxCoroutinesCore)
    implementation(deps.kotlinxCoroutinesAndroid)

    // Gson (used by GsonExt)
    implementation(deps.gson)

    // Lifecycle runtime (used by FlowExt)
    implementation(deps.androidxLifecycleRuntimeKtx)
}
