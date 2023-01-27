package com.example.database.di

import com.example.database.dataSource.DataBaseDataSource
import com.example.database.dataSource.DataBaseDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
interface DataStoreModule {

    @Binds
    fun DataBaseDataSourceImpl.binds(): DataBaseDataSource
}
