package com.example.weatherdetails.di

import androidx.lifecycle.ViewModel
import com.example.common.di.ViewModelKey
import com.example.weatherdetails.presentation.WeatherDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class WeatherDetailsViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(WeatherDetailsViewModel::class)
    abstract fun bindWeatherDetailsViewModel(viewModel: WeatherDetailsViewModel): ViewModel
}
