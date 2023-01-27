package com.example.app.di

import com.example.common.di.providers.ContextDependencyProvider
import com.example.common.navigation.RouterProvider
import com.example.model.domain.config.ConfigDependencyProvider
import com.example.network.di.NetworkDependenciesProvider

interface AppDependencies :
    ContextDependencyProvider,
    RouterProvider,
    ConfigDependencyProvider,
    NetworkDependenciesProvider
