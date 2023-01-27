package com.example.network.di

import com.example.network.utils.RetrofitProvider
import retrofit2.Retrofit

interface NetworkDependenciesProvider {
    fun provideRetrofitProvider(): RetrofitProvider
    fun provideRetrofit(): Retrofit
}
