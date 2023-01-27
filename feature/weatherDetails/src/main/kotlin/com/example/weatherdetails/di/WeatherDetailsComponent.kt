package com.example.weatherdetails.di

import com.example.common.di.ViewModelBuilderModule
import com.example.network.di.NetworkDependenciesProvider
import com.example.weatherdetails.presentation.WeatherDetailsFragment
import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Component
import dagger.Module
import me.vponomarenko.injectionmanager.x.XInjectionManager
import javax.inject.Scope

@WeatherDetailsScope
@Component(
    modules = [
        ViewModelBuilderModule::class,
        WeatherDetailsBindsModule::class,
        WeatherDetailsViewModelsModule::class,
        WeatherDetailsProvideModule::class,
        AssistedInjectModule::class
    ],
    dependencies = [
        NetworkDependenciesProvider::class
    ]
)
internal interface WeatherDetailsComponent {
    fun inject(fragment: WeatherDetailsFragment)

    class Initializer private constructor() {
        companion object {
            fun init(): WeatherDetailsComponent = DaggerWeatherDetailsComponent.builder()
                .networkDependenciesProvider(XInjectionManager.findComponent())
                .build()
        }
    }
}

@AssistedModule
@Module(includes = [AssistedInject_AssistedInjectModule::class])
interface AssistedInjectModule

@Scope
@Retention(AnnotationRetention.RUNTIME)
internal annotation class WeatherDetailsScope
