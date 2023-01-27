plugins {
    id(GradlePluginId.ANDROID_LIBRARY)
    id(GradlePluginId.KOTLIN_ANDROID)
    id(GradlePluginId.KOTLIN_SERIALIZATION)
    id(GradlePluginId.KOTLIN_KAPT)
    `android-kotlin-convention`
}

dependencies {
    dagger()

    implementation(Dependencies.Network.retrofit)
    implementation(Dependencies.Network.retrofitConverterMoshi)
    implementation(Dependencies.Network.okHttpLoggingInterceptor)

    implementation(Dependencies.Network.moshi)
    implementation(Dependencies.Network.moshiAdapters)
    implementation(Dependencies.Network.moshiKotlin)
    kapt(Dependencies.Network.moshiKotlinCodegen)
    implementation(Dependencies.Kotlin.serialization)


    implementation(Dependencies.timber)
}
