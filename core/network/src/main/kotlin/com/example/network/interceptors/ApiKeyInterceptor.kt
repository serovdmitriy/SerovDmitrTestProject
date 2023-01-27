package com.example.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Qualifier

class ApiKeyInterceptor @Inject constructor(@ApiKey private val apiKey: String) :
    Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url.newBuilder().addQueryParameter(API_KEY, apiKey).build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }

    private companion object {
        private const val API_KEY = "appid"
    }
}

@Qualifier
annotation class ApiKey
