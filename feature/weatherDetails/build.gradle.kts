plugins {
    id(GradlePluginId.ANDROID_LIBRARY)
    id(GradlePluginId.KOTLIN_ANDROID)
    id(GradlePluginId.KOTLIN_KAPT)
    id(GradlePluginId.SAFE_ARGS)
    id(GradlePluginId.KOTLIN_SERIALIZATION)
    id(GradlePluginId.FEATURE_CONVENTION)
    id(GradlePluginId.MAPS_PLATFORM)
    `android-kotlin-convention`
}

android {
    namespace = "com.example.weatherdetails"
}

dependencies {

    implementation(Dependencies.Kotlin.serialization)


    implementation(Dependencies.Android.maps)

    dagger()
    daggerAssistedInject()


}
