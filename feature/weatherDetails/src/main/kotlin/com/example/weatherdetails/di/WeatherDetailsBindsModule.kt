package com.example.weatherdetails.di

import com.example.weatherdetails.data.repository.WeatherDetailsRepositoryImpl
import com.example.weatherdetails.domain.repository.WeatherDetailsRepository
import dagger.Binds
import dagger.Module

@Module
internal interface WeatherDetailsBindsModule {

    @Binds
    fun WeatherDetailsRepositoryImpl.binds(): WeatherDetailsRepository
}
