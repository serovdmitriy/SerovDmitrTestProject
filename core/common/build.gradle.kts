plugins {
    id(GradlePluginId.ANDROID_LIBRARY)
    id(GradlePluginId.KOTLIN_ANDROID)
    id(GradlePluginId.KOTLIN_KAPT)
    `android-kotlin-convention`
}

dependencies {
    implementation(project(Modules.Core.network))
    implementation(project(Modules.Core.navigation))

    implementation(Dependencies.Android.materialDesign)
    implementation(Dependencies.Android.navigationFragmentKtx)
    implementation(Dependencies.Android.lifecycleLiveDataRun)

    implementation(Dependencies.Dagger.dagger2)
    kapt(Dependencies.Dagger.compilerKapt)

    implementation(Dependencies.timber)
    implementation(Dependencies.binding)
}
