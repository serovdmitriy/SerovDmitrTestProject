package com.example.weatherdetails.di

import com.example.weatherdetails.data.api.WeatherDetailsApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
internal class WeatherDetailsProvideModule {

    @Provides
    fun provideWeatherDetailsApi(retrofit: Retrofit): WeatherDetailsApi =
        retrofit.create(WeatherDetailsApi::class.java)
}
