import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions


android {
    defaultConfig {
        minSdk = AndroidConfigVersions.MIN_SDK_VERSION
        targetSdk = AndroidConfigVersions.TARGET_SDK_VERSION
        compileSdkVersion(AndroidConfigVersions.COMPILE_SDK_VERSION)

        val proguardFiles = rootProject.fileTree("proguard").files +
            getDefaultProguardFile("proguard-android-optimize.txt")
        proguardFiles(*proguardFiles.toTypedArray())

        resourceConfigurations.add("en")

        testApplicationId = "io.stormpay.test"

        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        getByName(BuildTypes.DEBUG) {
            isDebuggable = true
            isMinifyEnabled = false
        }

        getByName(BuildTypes.RELEASE) {
            isDebuggable = false
            isMinifyEnabled = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    testOptions {
        animationsDisabled = true
        unitTests.isReturnDefaultValues = true
    }

    (this as ExtensionAware).configure<KotlinJvmOptions> {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    if (this is LibraryExtension) {
        (this as LibraryExtension).buildFeatures {
            viewBinding = true
        }
    }
}

fun Project.android(configure: BaseExtension.() -> Unit) {
    extensions.configure("android", configure)
}