package com.example.citylist.di

import com.example.citylist.presentation.CityListFragment
import com.example.common.di.ViewModelBuilderModule
import com.example.common.di.providers.ContextDependencyProvider
import com.example.database.di.DaosModule
import com.example.database.di.DataStoreModule
import com.example.database.di.DatabaseModule
import com.example.network.di.NetworkDependenciesProvider
import dagger.Component
import me.vponomarenko.injectionmanager.x.XInjectionManager
import javax.inject.Scope

@CityListScope
@Component(
    modules = [
        ViewModelBuilderModule::class,
        CityListBindsModule::class,
        CityListViewModelsModule::class,
        DatabaseModule::class,
        DaosModule::class,
        DataStoreModule::class
    ],
    dependencies = [
        NetworkDependenciesProvider::class,
        ContextDependencyProvider::class,
    ]
)
internal interface CityListComponent {
    fun inject(fragment: CityListFragment)

    class Initializer private constructor() {
        companion object {
            fun init(): CityListComponent = DaggerCityListComponent.builder()
                .networkDependenciesProvider(XInjectionManager.findComponent())
                .contextDependencyProvider(XInjectionManager.findComponent())
                .build()
        }
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
internal annotation class CityListScope
