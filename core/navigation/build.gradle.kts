plugins {
    id(GradlePluginId.ANDROID_LIBRARY)
    id(GradlePluginId.KOTLIN_ANDROID)
    `android-kotlin-convention`
}

dependencies {
    api(Dependencies.Android.navigationFragmentKtx)
    implementation(Dependencies.timber)
}