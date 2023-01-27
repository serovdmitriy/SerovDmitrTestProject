package com.example.model.data.mapper

import com.example.model.data.api.currentWeather.*
import com.example.model.domain.city.CoordModel
import com.example.model.domain.currentWeather.*


fun CurrentWeatherResponse.toDomain() = CurrentWeather(
    base = base,
    clouds = clouds.toDomain(),
    cod = cod,
    coord = CoordModel(coord.lat, coord.lon),
    dt = dt,
    id = id,
    main = main.toDomain(),
    name = name,
    sys = sys.toDomain(),
    timezone = timezone,
    visibility = visibility,
    weather = weather.map { it.toDomain() },
    wind = wind.toDomain()
)

fun Clouds.toDomain() = CloudsDomain(
    all = all
)

fun Main.toDomain() = MainDomain(
    feelsLike = feelsLike,
    grndLevel = grndLevel,
    humidity = humidity,
    pressure = pressure,
    seaLevel = seaLevel,
    temp = temp,
    tempMax = tempMax,
    tempMin = tempMin
)

fun Sys.toDomain() = SysDomain(
    country = country,
    sunrise = sunrise,
    sunset = sunset
)

fun Weather.toDomain() = WeatherDomain(
    description = description,
    icon = icon,
    id = id,
    main = main
)

fun Wind.toDomain() = WindDomain(
    deg = deg,
    gust = gust,
    speed = speed
)

