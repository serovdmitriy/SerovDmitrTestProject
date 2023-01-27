// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(GradleOldWayPlugins.ANDROID_GRADLE)
        classpath(GradleOldWayPlugins.KOTLIN_GRADLE)
        classpath(GradleOldWayPlugins.SAFE_ARGS)
        classpath(GradleOldWayPlugins.KTLINT)
        classpath(GradleOldWayPlugins.KOTLIN_SERIALIZATION)
    }
}

plugins {
    id(GradlePluginId.KTLINT) version GradlePluginVersions.KTLINT
    id (GradlePluginId.MAPS_PLATFORM) version GradlePluginVersions.MAPS_PLATFORM apply false
}

subprojects {
    val skipModule = "model"
    if(skipModule.contains(project.name)) {
        print("Gradle skip this module")
    } else {
        apply(plugin = "conventions.detekt-convention")
        apply(plugin = "conventions.ktlint-convention")

        val detekt by tasks
        val ktlintCheck by tasks
        // Run before push commit and create MR
        tasks.register("codeStyleCheck") {
            group = "verification"
            description = "Combined codestyle checks"
            dependsOn(ktlintCheck, detekt)
        }
    }

    // Enable experimental coroutines APIs, including Flow
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf(
                "-Xopt-in=kotlin.RequiresOptIn"
            )
        }
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}