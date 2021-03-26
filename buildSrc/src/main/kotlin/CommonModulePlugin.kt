import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class CommonModulePlugin : Plugin<Project> {

    override fun apply(project: Project) {
        // apply plugin common to all projects

        // configure the android block
        val androidExtensions = project.extensions.getByName("android")

        if (androidExtensions is BaseExtension) {

            androidExtensions.apply {

                compileSdkVersion(30)

                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_1_8
                    targetCompatibility = JavaVersion.VERSION_1_8
                }

                project.tasks.withType(KotlinCompile::class.java).configureEach {
                    kotlinOptions {
                        jvmTarget = JavaVersion.VERSION_1_8.toString()
                    }
                }

                testOptions {
                    unitTests.isReturnDefaultValues = true
                }

                defaultConfig {

                    minSdkVersion(21)
                    targetSdkVersion(30)
                    versionCode = 1
                    versionName = "1.0"

                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }

                buildFeatures.viewBinding = true

                when (this) {
                    is LibraryExtension -> {
                        defaultConfig {
                            // apply the pro guard rules for library
                            consumerProguardFiles("consumer-rules.pro")
                        }
                    }

                    is AppExtension -> {

                        buildTypes {
                            getByName("release") {
                                isMinifyEnabled = true
                                proguardFiles(
                                    getDefaultProguardFile("proguard-android-optimize.txt"),
                                    "proguard-rules.pro"
                                )
                            }
                        }
                    }
                }
            }
        }

        // dependencies common to all projects
    }
}