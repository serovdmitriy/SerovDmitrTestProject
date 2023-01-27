package com.example.app.di

import android.app.Application
import android.content.Context
import com.example.app.config.DevConfig
import com.example.app.presentation.router.MainRouter
import com.example.common.navigation.Router
import com.example.model.domain.config.Config
import com.example.network.di.BaseURL
import com.example.network.interceptors.ApiKey
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@SuppressWarnings("TooManyFunctions")
@Module(includes = [DependenciesModules::class])
class AppModule {

    @Singleton
    @Provides
    fun provideContext(app: Application): Context = app.applicationContext

    @Provides
    @BaseURL
    fun provideBaseUrl(config: Config): String = config.baseUrl

    @Provides
    @ApiKey
    fun provideApiKey(config: Config): String = config.apiKey

    @Singleton
    @Provides
    fun provideMainRouter(): Router = MainRouter()

    @Singleton
    @Provides
    fun provideAppConfig(): Config {
        return DevConfig()
    }
}
