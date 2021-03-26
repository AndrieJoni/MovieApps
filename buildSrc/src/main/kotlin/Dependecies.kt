import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

object Dependecies {

    const val KOTLIN_LIB = "org.jetbrains.kotlin:kotlin-stdlib:${Version.Kotlin.kotlin_version}"
    const val ANDROID_KTX_CORE = "androidx.core:core-ktx:${Version.Android.androidx_ktx_core_version}"
    const val GOOGLE_MATERIAL =
        "com.google.android.material:material:${Version.Android.google_material_version}"
    const val APP_COMPAT =
        "androidx.appcompat:appcompat:${Version.Android.androidx_app_compat_version}"
    const val ACTIVITY = "androidx.activity:activity-ktx:${Version.Android.ACTIVITY}"
    const val FRAGMENT = "androidx.fragment:fragment-ktx:${Version.Android.FRAGMENT}"
    const val WORKER_MANAGER_RUNTIME =
        "androidx.work:work-runtime-ktx:${Version.Android.WORKER_MANAGER_RUNTIME}"
    const val CONSTRAINT_LAYOUT =
        "androidx.constraintlayout:constraintlayout:${Version.Android.androidx_constraint_layout_version}"
    const val PAGING =
        "androidx.paging:paging-runtime-ktx:${Version.Android.androidx_paging_version}"

    const val CARD_VIEW = "androidx.cardview:cardview:${Version.Android.androidx_card_view_version}"
    const val RECYCLER_VIEW =
        "androidx.recyclerview:recyclerview:${Version.Android.androidx_recycler_view_version}"
    const val MULTIDEX = "androidx.multidex:multidex:${Version.Android.androidx_multidex_version}"

    const val LIFECYCLE_EXTENSION =
        "androidx.lifecycle:lifecycle-extensions:${Version.Android.androidx_lifecycle_extension}"
    const val LIFECYCLE_VIEW_MODEL =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.Android.androidx_lifecycle_ktx_version}"
    const val LIFECYCLE_RUNTIME =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Version.Android.androidx_lifecycle_ktx_version}"
    const val LIFECYCLE_LIVE_DATA =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Version.Android.androidx_lifecycle_ktx_version}"

    const val ROOM_RUNTIME = "androidx.room:room-runtime:${Version.Android.androidx_room_version}"
    const val ROOM_KTX = "androidx.room:room-ktx:${Version.Android.androidx_room_version}"
    const val ROOM_COMPILER =
        "androidx.room:room-compiler:${Version.Android.androidx_room_version}"// KAPT

    const val HILT = "com.google.dagger:hilt-android:2.31-alpha"
    const val HILT_VIEW_MODEL = "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03"
    const val HILT_GOOGLE_COMPILER = "com.google.dagger:hilt-android-compiler:2.31.2-alpha"// KAPT
    const val HILT_ANDROID_COMPILER = "androidx.hilt:hilt-compiler:1.0.0-alpha03"// KAPT

    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Version.Retrofit.retrofit_version}"
    const val RETROFIT_GSON =
        "com.squareup.retrofit2:converter-gson:${Version.Retrofit.retrofit_version}"
    const val RETROFIT_LOGGING =
        "com.squareup.okhttp3:logging-interceptor:${Version.Retrofit.okhttp_logging_version}"

    const val PICASSO = "com.squareup.picasso:picasso:${Version.Image.picasso_version}"

    const val THREETEN =
        "com.jakewharton.threetenabp:threetenabp:${Version.Threeten.threeten_abp_version}"

    const val JUNIT = "junit:junit:${Version.Testing.junit_version}"// TEST IMPLEMENT
    const val ANDROID_JUNIT =
        "androidx.test.ext:junit:${Version.Testing.test_ext_junit_version}" // ANDROID TEST IMPLEMENT
    const val ANDROID_ESPRESSO =
        "androidx.test.espresso:espresso-core:${Version.Testing.test_espresso_core_version}"// ANDROID TEST IMPLEMENT

    const val HOME_FEATURE = ":features:home"
    const val MOVIE_DETAILS_FEATURE = ":features:moviedetails"
    const val MOVIE_FAVORITE_FEATURE = ":features:moviefavorite"
    const val DOMAIN = ":domain"
    const val COMMON_DATA_FEATURE = ":features:commondata"
    const val BASE_DATA_LIBRARY = ":libraries:basedata"
    const val UTIL_LIBRARY = ":libraries:util"
    const val COMMON_UI_FEATURE = ":features:commonui"
}

fun Project.addAndroidUiLibrary() {

    dependencies {
        add(
            "implementation",
            "org.jetbrains.kotlin:kotlin-stdlib:${Version.Kotlin.kotlin_version}"
        )
    }
}

fun Project.addRoomLibrary() {
    dependencies {
        add("implementation", Dependecies.ROOM_KTX)
        add("implementation", Dependecies.ROOM_RUNTIME)
        add("kapt", Dependecies.ROOM_COMPILER)
    }
}

fun Project.addLifecycleLibrary() {
    dependencies {
        add("implementation", Dependecies.LIFECYCLE_EXTENSION)
        add("implementation", Dependecies.LIFECYCLE_LIVE_DATA)
        add("implementation", Dependecies.LIFECYCLE_RUNTIME)
        add("implementation", Dependecies.LIFECYCLE_VIEW_MODEL)
    }
}

fun Project.addHiltLibrary() {
    dependencies {
        add("implementation", Dependecies.HILT)
        add("implementation", Dependecies.HILT_ANDROID_COMPILER)
        add("implementation", Dependecies.HILT_GOOGLE_COMPILER)
        add("implementation", Dependecies.HILT_VIEW_MODEL)
    }
}

fun Project.addRetrofitLibrary() {
    dependencies {
        add("implementation", Dependecies.RETROFIT)
        add("implementation", Dependecies.RETROFIT_GSON)
        add("implementation", Dependecies.RETROFIT_LOGGING)
    }
}

fun Project.addTestingLibrary() {
    dependencies {
        add("testImplementation", Dependecies.JUNIT)
        add("androidTestImplementation", Dependecies.ANDROID_JUNIT)
        add("androidTestImplementation", Dependecies.ANDROID_ESPRESSO)
    }
}

fun Project.addAll() {
    dependencies {

        add(
            "implementation",
            Dependecies.KOTLIN_LIB
        )

        add("implementation", Dependecies.ANDROID_KTX_CORE)
        add("implementation", Dependecies.GOOGLE_MATERIAL)
        add("implementation", Dependecies.ACTIVITY)
        add("implementation", Dependecies.FRAGMENT)
        add("implementation", Dependecies.APP_COMPAT)
        add("implementation", Dependecies.WORKER_MANAGER_RUNTIME)
        add("implementation", Dependecies.CONSTRAINT_LAYOUT)
        add("implementation", Dependecies.PAGING)
        add("implementation", Dependecies.CARD_VIEW)
        add("implementation", Dependecies.RECYCLER_VIEW)
        add("implementation", Dependecies.MULTIDEX)
        add("implementation", Dependecies.PICASSO)
        add("implementation", Dependecies.THREETEN)

        add("implementation", Dependecies.ROOM_KTX)
        add("implementation", Dependecies.ROOM_RUNTIME)
        add("kapt", Dependecies.ROOM_COMPILER)

        add("implementation", Dependecies.LIFECYCLE_EXTENSION)
        add("implementation", Dependecies.LIFECYCLE_LIVE_DATA)
        add("implementation", Dependecies.LIFECYCLE_RUNTIME)
        add("implementation", Dependecies.LIFECYCLE_VIEW_MODEL)

        add("implementation", Dependecies.HILT)
        add("implementation", Dependecies.HILT_ANDROID_COMPILER)
        add("kapt", Dependecies.HILT_GOOGLE_COMPILER)
        add("kapt", Dependecies.HILT_VIEW_MODEL)

        add("implementation", Dependecies.RETROFIT)
        add("implementation", Dependecies.RETROFIT_GSON)
        add("implementation", Dependecies.RETROFIT_LOGGING)

        add("testImplementation", Dependecies.JUNIT)
        add("androidTestImplementation", Dependecies.ANDROID_JUNIT)
        add("androidTestImplementation", Dependecies.ANDROID_ESPRESSO)
    }
}
