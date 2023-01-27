package com.example.weatherdetails.data.repository

import com.example.model.data.mapper.toDomain
import com.example.model.domain.currentWeather.CurrentWeather
import com.example.network.result.Result
import com.example.network.result.map
import com.example.weatherdetails.data.api.WeatherDetailsApi
import com.example.weatherdetails.domain.repository.WeatherDetailsRepository
import javax.inject.Inject

internal class WeatherDetailsRepositoryImpl @Inject constructor(
    private val api: WeatherDetailsApi
) : WeatherDetailsRepository {
    override suspend fun currentWeather(lat: Double, lon: Double): Result<CurrentWeather> {
        return api.currentWeather(lat, lon).map { it.toDomain() }
    }
}
