package com.example.weatherdetails.data.api

import com.example.model.data.api.currentWeather.CurrentWeatherResponse
import com.example.network.result.Result
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

internal interface WeatherDetailsApi {

    @GET("weather")
    suspend fun currentWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): Result<CurrentWeatherResponse>
}
