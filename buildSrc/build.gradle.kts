plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.19.0")
    implementation("com.android.tools.build:gradle:7.1.1")
    implementation(kotlin("gradle-plugin", version = "1.7.0"))
}

gradlePlugin {
    plugins {
        register("featureConvention") {
            id = "feature.convention"
            implementationClass = "FeatureConventionPlugin"
        }
    }
}