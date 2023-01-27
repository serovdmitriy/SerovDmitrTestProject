package com.example.database.di

import com.example.database.AppDatabase
import com.example.database.dao.CityDao
import dagger.Module
import dagger.Provides

@Module
class DaosModule {

    @Provides
    fun providesCityDao(
        database: AppDatabase,
    ): CityDao = database.cityDao()
}
