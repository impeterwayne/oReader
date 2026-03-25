import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidJniConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension> {
                defaultConfig {
                    externalNativeBuild {
                        ndkBuild {
                            arguments += listOf("-j4")
                        }
                    }
                    if (project.hasProperty("ABI_FILTERS")) {
                        ndk {
                            val filters = (project.property("ABI_FILTERS") as String)
                                .split(",")
                                .map { it.trim() }
                            abiFilters.addAll(filters)
                        }
                    } else {
                        ndk {
                            abiFilters.addAll(listOf("arm64-v8a", "armeabi-v7a", "x86", "x86_64"))
                        }
                    }
                }

                externalNativeBuild {
                    ndkBuild {
                        path = file("jni/libmupdf/platform/java/Android.mk")
                    }
                }
            }
        }
    }
}
