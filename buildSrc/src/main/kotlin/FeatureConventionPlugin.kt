import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

class FeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                add("implementation", Dependencies.Android.core)
                add("implementation", Dependencies.Android.appCompat)
                add("implementation", Dependencies.Android.fragmentKtx)
                add("implementation", Dependencies.Android.recyclerview)
                add("implementation", Dependencies.Android.constraintLayout)
                add("implementation", Dependencies.Android.navigationFragmentKtx)
                add("implementation", Dependencies.Android.navigationUIKtx)
                add("implementation", Dependencies.Android.lifecycleLiveDataRun)
                add("implementation", Dependencies.Android.lifecycleViewModelKtx)
                add("implementation", Dependencies.Kotlin.coroutinesCore)
                add("implementation", Dependencies.timber)
                add("implementation", Dependencies.leakCanary)
                add("implementation", Dependencies.binding)
                add("implementation", Dependencies.Dagger.dagger2)
                add("implementation", Dependencies.Network.retrofit)
                add("implementation", Dependencies.Dagger.componentManager)
                add("implementation", Dependencies.Dagger.compilerKapt)


                add("implementation", project(Modules.Core.network))
                add("implementation", project(Modules.Core.common))
                add("implementation", project(Modules.Core.navigation))
                add("implementation", project(Modules.Core.model))
            }
        }
    }
}
