package dependencies

object Build {
    val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
    val kotlin_gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    val navigation_plugin = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
    val hilt_plugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
}