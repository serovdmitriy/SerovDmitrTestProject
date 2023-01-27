plugins {
    id(GradlePluginId.ANDROID_LIBRARY)
    id(GradlePluginId.KOTLIN_ANDROID)
    id(GradlePluginId.KOTLIN_KAPT)
    `android-kotlin-convention`
}

android {
    namespace = "com.example.database"
}

dependencies {
    implementation(project(Modules.Core.model))

    implementation(Dependencies.Kotlin.serialization)

    dagger()

    implementation(Dependencies.Room.room)
    kapt(Dependencies.Room.compilerRoom)
}
