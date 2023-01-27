package com.example.model.domain.currentWeather

import com.example.model.domain.city.CoordModel

data class CurrentWeather(
    val base: String,
    val clouds: CloudsDomain,
    val cod: Int,
    val coord: CoordModel,
    val dt: Int,
    val id: Int,
    val main: MainDomain,
    val name: String,
    val sys: SysDomain,
    val timezone: Int,
    val visibility: Int,
    val weather: List<WeatherDomain>,
    val wind: WindDomain
)