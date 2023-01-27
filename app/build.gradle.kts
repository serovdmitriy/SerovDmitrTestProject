plugins {
    id(GradlePluginId.ANDROID_APPLICATION)
    id(GradlePluginId.KOTLIN_ANDROID)
    id(GradlePluginId.KOTLIN_KAPT)
    `android-kotlin-convention`
}

dependencies {
    implementation(project(Modules.Core.common))
    implementation(project(Modules.Core.model))
    implementation(project(Modules.Core.database))

    implementation(project(Modules.Feature.cityList))
    implementation(project(Modules.Feature.weatherDetails))

    debugImplementation(Dependencies.leakCanary)
    debugImplementation(Dependencies.Kotlin.serialization)


    navigation()
    dagger()
    network()

    implementation(Dependencies.Android.core)
    implementation(Dependencies.Android.appCompat)
    implementation(Dependencies.timber)
    implementation(Dependencies.binding)
}
