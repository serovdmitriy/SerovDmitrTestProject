package com.example.citylist.di

import com.example.citylist.data.repository.CityListRepositoryImpl
import com.example.citylist.domain.repository.CityListRepository
import dagger.Binds
import dagger.Module

@Module
internal abstract class CityListBindsModule {
    @Binds
    abstract fun CityListRepositoryImpl.binds(): CityListRepository
}
