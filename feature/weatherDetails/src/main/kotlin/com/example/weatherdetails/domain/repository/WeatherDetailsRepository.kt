package com.example.weatherdetails.domain.repository

import com.example.model.domain.currentWeather.CurrentWeather
import com.example.network.result.Result

internal interface WeatherDetailsRepository {

    suspend fun currentWeather(
        lat: Double,
        lon: Double,
    ): Result<CurrentWeather>
}
