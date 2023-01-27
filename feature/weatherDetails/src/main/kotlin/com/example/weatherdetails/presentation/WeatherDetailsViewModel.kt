package com.example.weatherdetails.presentation

import androidx.lifecycle.viewModelScope
import com.example.common.base.BaseViewModel
import com.example.common.utils.Text
import com.example.model.domain.city.CoordModel
import com.example.network.result.call
import com.example.weatherdetails.R
import com.example.weatherdetails.domain.repository.WeatherDetailsRepository
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.math.roundToInt

internal data class Weather(
    val description: Text = Text.Str(""),
    val currentAirTemperature: Text = Text.Str(""),
    val minMaxTemp: Text = Text.Str(""),
    val airHumidity: Text = Text.Str(""),
    val windSpeed: Text = Text.Str("")
)

internal class WeatherDetailsViewModel @AssistedInject constructor(
    @Assisted private val coord: CoordModel,
    private val weatherDetails: WeatherDetailsRepository
) : BaseViewModel() {

    @AssistedInject.Factory
    interface Factory {
        fun create(coord: CoordModel): WeatherDetailsViewModel
    }

    val currentWeather = MutableStateFlow(Weather())

    init {
        getCurrentWeather()
    }

    private fun getCurrentWeather() {
        viewModelScope.launchWithProgress {
            weatherDetails.currentWeather(coord.lat, coord.lon).call(
                success = { result ->
                    val weather = result.value

                    currentWeather.value = Weather(
                        description = Text.ResParams(R.string.description, listOf(weather.name)),
                        currentAirTemperature = Text.ResParams(
                            R.string.current_air_temperature,
                            listOf(weather.main.temp.toCelsius())
                        ),
                        minMaxTemp = Text.ResParams(
                            R.string.min_max_temp,
                            listOf(
                                weather.main.tempMax.toCelsius(),
                                weather.main.tempMin.toCelsius()
                            )
                        ),
                        airHumidity = Text.ResParams(
                            R.string.air_humidity,
                            listOf(weather.main.humidity)
                        ),
                        windSpeed = Text.ResParams(
                            R.string.wind_speed,
                            listOf(weather.wind.speed)
                        ),
                    )
                },
                failure = { result ->
                    showError(Text.Str(result.error.message ?: ""))
                }
            )
        }
    }

    private fun Double.toCelsius(): Int {
        return (this - KELVIN).roundToInt()
    }

    companion object {
        private const val KELVIN = 273.15
    }
}
