package com.example.citylist.di

import androidx.lifecycle.ViewModel
import com.example.citylist.presentation.CityListViewModel
import com.example.common.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class CityListViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(CityListViewModel::class)
    abstract fun bindCityListViewModel(viewModel: CityListViewModel): ViewModel
}
