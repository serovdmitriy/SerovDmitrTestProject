package com.example.model.domain.currentWeather


data class WeatherDomain(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)