plugins {
    id(GradlePluginId.ANDROID_LIBRARY)
    id(GradlePluginId.KOTLIN_ANDROID)
    id(GradlePluginId.KOTLIN_SERIALIZATION)
    id(GradlePluginId.KOTLIN_PARCELIZE)
    id(GradlePluginId.KOTLIN_KAPT)
    `android-kotlin-convention`
}

android {
    namespace = "com.example.model"
}

dependencies {
    implementation(Dependencies.Kotlin.serialization)

    api(Dependencies.Network.moshi)
    kapt(Dependencies.Network.moshiKotlinCodegen)
}
