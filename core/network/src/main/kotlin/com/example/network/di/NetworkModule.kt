package com.example.network.di

import com.example.network.BuildConfig
import com.example.network.interceptors.ApiKeyInterceptor
import com.example.network.result.errorHandler.NetworkErrorHandler
import com.example.network.result.errorHandler.NetworkErrorHandlerImpl
import com.example.network.utils.RetrofitProvider
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import javax.inject.Qualifier

@Module
class NetworkModule {

    /**
     * Default Okhttp builder with base settings
     */
    private fun createOkhttpBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
    }

    /**
     * Default Okhttp logger
     */
    private fun createHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor { message ->
            Timber.tag("OkHttp").d(message)
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    fun provideOkhttpClient(
        apiKeyInterceptor: ApiKeyInterceptor
    ): OkHttpClient {
        val builder = createOkhttpBuilder()

        builder.addInterceptor(apiKeyInterceptor)

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(createHttpLoggingInterceptor())
        }

        return builder.build()
    }

    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    fun provideNetworkErrorHandler(moshi: Moshi): NetworkErrorHandler =
        NetworkErrorHandlerImpl(moshi)

    @Provides
    fun provideRetrofitProvider(
        okHttp: OkHttpClient,
        moshi: Moshi,
        errorHandler: NetworkErrorHandler,
    ): RetrofitProvider {
        return RetrofitProvider(
            okHttpClient = okHttp,
            converterFactory = MoshiConverterFactory.create(moshi),
            networkErrorHandler = errorHandler
        )
    }

    /**
     * Default Retrofit client with baseUrl
     *
     * if need change baseUrl use RetrofitProvider
     */
    @Provides
    fun provideRetrofit(retrofitProvider: RetrofitProvider, @BaseURL baseUrl: String): Retrofit {
        return retrofitProvider.provide(baseUrl).build()
    }
}

@Qualifier
annotation class BaseURL
