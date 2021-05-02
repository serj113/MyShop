package dependencies

object Dependencies {
    // region fragment
    val android_core_ktx = "androidx.core:core-ktx:${Versions.android_core_ktx}"
    val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    val material = "com.google.android.material:material:${Versions.material}"
    val legacy_support = "androidx.legacy:legacy-support-v4:${Versions.legacy_support}"
    val recycle_view = "androidx.recyclerview:recyclerview:${Versions.recycle_view}"
    val constraint_layout = "androidx.constraintlayout:constraintlayout:${Versions.constraint_layout}"
    val paging = "androidx.paging:paging-runtime-ktx:${Versions.paging}"

    val fragment_ktx = "androidx.fragment:fragment-ktx:${Versions.fragment}"

    val lifecycle_extension = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    val lifecycle_viewmodel_ktx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    val lifecycle_runtime_ktx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"

    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    val glide_annotation = "com.github.bumptech.glide:compiler:${Versions.glide}"

    val navigation_fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    val navigation_ui = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    val android_hilt_viewmodel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.android_hilt_viewmodel}"
    val android_hilt_compiler_kapt = "androidx.hilt:hilt-compiler:${Versions.android_hilt_viewmodel}"
    // end region

    val coroutine_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    val coroutine_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"

    val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    val hilt_compiler_kapt = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"

    val kotlin_standard_library = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    val moshi = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
    val moshi_codegen_kapt = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"

    val multidex = "androidx.multidex:multidex:${Versions.multidex}"

    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val retrofit_moshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"

    val room = "androidx.room:room-runtime:${Versions.room}"
    val room_ktx = "androidx.room:room-ktx:${Versions.room}"
    val room_kapt = "androidx.room:room-compiler:${Versions.room}"

    // region test
    val elmyr = "com.github.xgouchet.Elmyr:junit4:${Versions.elmyr}"
    val junit = "junit:junit:${Versions.junit}"
    val mockk = "io.mockk:mockk:${Versions.mockk}"
    val hamkrest = "com.natpryce:hamkrest:${Versions.hamkrest}"
    val arch_test = "androidx.arch.core:core-testing:${Versions.arch_test}"
    val coroutine_test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    // end region
}