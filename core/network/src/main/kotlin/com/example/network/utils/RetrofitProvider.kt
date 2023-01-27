package com.example.network.utils

import com.example.network.result.errorHandler.NetworkErrorHandler
import com.example.network.result.retrofit.ResultAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit

class RetrofitProvider(
    private val okHttpClient: OkHttpClient,
    private val converterFactory: Converter.Factory,
    private val networkErrorHandler: NetworkErrorHandler
) {
    fun provide(baseUrl: String): Retrofit.Builder {
        return Retrofit.Builder().apply {
            baseUrl(baseUrl)
            addCallAdapterFactory(ResultAdapterFactory(networkErrorHandler))
            client(okHttpClient)
            addConverterFactory(converterFactory)
        }
    }
}
