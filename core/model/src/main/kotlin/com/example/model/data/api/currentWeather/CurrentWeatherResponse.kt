package com.example.model.data.api.currentWeather


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrentWeatherResponse(
    @Json(name = "base")
    val base: String = "",
    @Json(name = "clouds")
    val clouds: Clouds = Clouds(),
    @Json(name = "cod")
    val cod: Int = 0,
    @Json(name = "coord")
    val coord: Coord = Coord(),
    @Json(name = "dt")
    val dt: Int = 0,
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "main")
    val main: Main = Main(),
    @Json(name = "name")
    val name: String = "",
    @Json(name = "rain")
    val rain: Rain = Rain(),
    @Json(name = "sys")
    val sys: Sys = Sys(),
    @Json(name = "timezone")
    val timezone: Int = 0,
    @Json(name = "visibility")
    val visibility: Int = 0,
    @Json(name = "weather")
    val weather: List<Weather> = listOf(),
    @Json(name = "wind")
    val wind: Wind = Wind()
)