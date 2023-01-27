package com.example.app.di

import com.example.common.di.CoreBaseModule
import com.example.database.di.DatabaseModule
import com.example.network.di.NetworkModule
import dagger.Module

/**
 * Main app dependencies module
 * this module need extension when we have common di modules
 */
@Module(
    includes = [
        NetworkModule::class,
        CoreBaseModule::class,
        DatabaseModule::class
    ]
)
class DependenciesModules
