package com.example.model.domain.config

interface ConfigDependencyProvider {
    fun provideAppConfig(): Config
}
