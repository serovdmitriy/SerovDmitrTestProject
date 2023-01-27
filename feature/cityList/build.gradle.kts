plugins {
    id(GradlePluginId.ANDROID_LIBRARY)
    id(GradlePluginId.KOTLIN_ANDROID)
    id(GradlePluginId.KOTLIN_KAPT)
    id(GradlePluginId.SAFE_ARGS)
    id(GradlePluginId.KOTLIN_SERIALIZATION)
    id(GradlePluginId.FEATURE_CONVENTION)
    `android-kotlin-convention`
}

android {
    namespace = "com.example.citylist"
}

dependencies {
    implementation(project(Modules.Core.database))

    implementation(Dependencies.Kotlin.serialization)
    implementation(Dependencies.Android.paging)
    implementation(Dependencies.Glide.glide)
    kapt(Dependencies.Glide.compiler)
}