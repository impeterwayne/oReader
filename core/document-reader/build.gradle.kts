plugins {
    id("codebase.android.library")
    id("codebase.android.jni")
}

android {
    namespace = "com.steve.oreader.core.documentreader"

    sourceSets {
        getByName("main") {
            manifest.srcFile("jni/AndroidManifest.xml")
            java.setSrcDirs(listOf("jni/libmupdf/platform/java/src"))
        }
    }
}
