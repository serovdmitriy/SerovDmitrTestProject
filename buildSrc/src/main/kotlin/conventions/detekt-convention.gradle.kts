package conventions

import GradlePluginId
import gradle.kotlin.dsl.accessors._e22d22ce689a20181b312a7a3bfeafc1.detekt
import gradle.kotlin.dsl.accessors._e22d22ce689a20181b312a7a3bfeafc1.detektPlugins
import io.gitlab.arturbosch.detekt.Detekt
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType

plugins {
    id("io.gitlab.arturbosch.detekt")
}

detekt {
    config = rootProject.files("config/detekt/config.yml")
    baseline = rootProject.file("config/detekt/baseline.xml")
    autoCorrect = true
    parallel = true
    reportsDir = rootProject.file("build/reports/detekt")
}

tasks.withType<Detekt>().configureEach {
    jvmTarget = "11"

    reports {
        html.required.set(true)
        xml.required.set(false)
        txt.required.set(false)
        sarif.required.set(false)
    }
}

dependencies {
    detektPlugins(GradlePluginId.DETEKT_FORMATTING)
}