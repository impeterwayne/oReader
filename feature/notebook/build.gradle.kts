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
    implementation(deps.activityCompose)
    implementation(deps.androidxLifecycleRuntimeCompose)
    implementation("androidx.compose.material:material")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.graphics:graphics-core:1.0.4")
    implementation("androidx.input:input-motionprediction:1.0.0")

    // Onyx SDK for e-ink stylus input and EPD refresh
    implementation("com.onyx.android.sdk:onyxsdk-device:1.3.2") {
        exclude(group = "com.android.support", module = "support-compat")
    }
    implementation("com.onyx.android.sdk:onyxsdk-pen:1.5.1") {
        exclude(group = "com.android.support", module = "support-compat")
        exclude(group = "com.android.support", module = "appcompat-v7")
        exclude(group = "com.onyx.android.sdk", module = "onyxsdk-geometry")
    }
    implementation("com.onyx.android.sdk:onyxsdk-base:1.8.3") {
        exclude(group = "com.android.support", module = "support-compat")
        exclude(group = "com.android.support", module = "appcompat-v7")
    }
    implementation("org.lsposed.hiddenapibypass:hiddenapibypass:6.1")

    // RxJava (used by OnyxInputHandler / RawInputManager)
    implementation("io.reactivex.rxjava2:rxjava:2.2.21")
    implementation("io.reactivex.rxjava2:rxandroid:2.1.1")

    // Compose icons (Feather) for toolbar
    implementation("br.com.devsrsouza.compose.icons.android:feather:1.0.0")

    // Material Icons Extended (for additional icons used in editor UI)
    implementation("androidx.compose.material:material-icons-extended")
}
