import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

fun DependencyHandler.network() {
    implementation(project(Modules.Core.network))
    implementation(Dependencies.Network.retrofit)
    implementation(Dependencies.Network.moshi)
}

fun DependencyHandler.navigation() {
    implementation(project(Modules.Core.navigation))
    implementation(Dependencies.Android.navigationFragmentKtx)
    implementation(Dependencies.Android.navigationUIKtx)
}

fun DependencyHandler.dagger() {
    implementation(Dependencies.Dagger.dagger2)
    implementation(Dependencies.Dagger.componentManager)
    kapt(Dependencies.Dagger.compilerKapt)
}

fun DependencyHandler.daggerAssistedInject() {
    compileOnly(Dependencies.Dagger.assistedInjectAnnotationsCompile)
    kapt(Dependencies.Dagger.assistedInjectProcessorKapt)
}

private fun DependencyHandler.implementation(depName: Any) {
    add("implementation", depName)
}
private fun DependencyHandler.project(depName: ProjectDependency) {
    add("implementation", depName)
}
private fun DependencyHandler.kapt(depName: String) {
    add("kapt", depName)
}
private fun DependencyHandler.compileOnly(depName: String) {
    add("compileOnly", depName)
}
private fun DependencyHandler.testImplementation(depName: Any) {
    add("testImplementation", depName)
}
private fun DependencyHandler.androidTestImplementation(depName: Any) {
    add("androidTestImplementation", depName)
}
private fun DependencyHandler.debugImplementation(depName: Any) {
    add("debugImplementation", depName)
}
