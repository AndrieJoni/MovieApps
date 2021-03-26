plugins {
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        register("module-plugin") {
            id = "module-plugin"
            implementationClass = "CommonModulePlugin"
        }
    }
}

repositories {
    google()
    mavenCentral()
    jcenter()
}

dependencies {
    compileOnly(gradleApi())
    implementation("com.android.tools.build:gradle:4.1.2")
    implementation(kotlin("gradle-plugin", "1.4.21"))
    implementation("androidx.navigation:navigation-safe-args-gradle-plugin:2.3.3")
    implementation("com.google.dagger:hilt-android-gradle-plugin:2.28-alpha")
}
