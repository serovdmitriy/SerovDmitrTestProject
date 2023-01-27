package com.example.app.config

import com.example.model.domain.config.Config

internal class DevConfig : Config {
    override val baseUrl: String = "https://api.openweathermap.org/data/2.5/"
    override val apiKey: String = "02de63ccf028fea6ec3337636ffb00ac"
}
