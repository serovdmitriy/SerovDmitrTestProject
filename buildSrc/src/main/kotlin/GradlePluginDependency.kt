object GradlePluginVersions {
    const val ANDROID_GRADLE = "7.1.1"
    const val KOTLIN = Versions.kotlin
    const val SAFE_ARGS = Versions.navigation
    const val DETEKT = "1.19.0"
    const val KTLINT = "10.2.1"
    const val MAPS_PLATFORM = "2.0.1"
}

object GradlePluginId {
    const val FEATURE_CONVENTION = "feature.convention"
    const val ANDROID_APPLICATION = "com.android.application"
    const val ANDROID_LIBRARY = "com.android.library"
    const val KOTLIN_SERIALIZATION = "kotlinx-serialization"
    const val KOTLIN_ANDROID = "org.jetbrains.kotlin.android"
    const val KOTLIN_PARCELIZE = "kotlin-parcelize"
    const val KOTLIN_KAPT = "org.jetbrains.kotlin.kapt"
    const val SAFE_ARGS = "androidx.navigation.safeargs.kotlin"
    const val DETEKT_FORMATTING = "io.gitlab.arturbosch.detekt:detekt-formatting:${GradlePluginVersions.DETEKT}"
    const val KTLINT = "org.jlleitschuh.gradle.ktlint"
    const val MAPS_PLATFORM = "com.google.android.libraries.mapsplatform.secrets-gradle-plugin"
}

object GradleOldWayPlugins {
    const val ANDROID_GRADLE = "com.android.tools.build:gradle:${GradlePluginVersions.ANDROID_GRADLE}"
    const val KOTLIN_GRADLE = "org.jetbrains.kotlin:kotlin-gradle-plugin:${GradlePluginVersions.KOTLIN}"
    const val SAFE_ARGS = "androidx.navigation:navigation-safe-args-gradle-plugin:${GradlePluginVersions.SAFE_ARGS}"
    const val KTLINT = "org.jlleitschuh.gradle:ktlint-gradle:${GradlePluginVersions.KTLINT}"
    const val KOTLIN_SERIALIZATION = "org.jetbrains.kotlin:kotlin-serialization:${GradlePluginVersions.KOTLIN}"
}