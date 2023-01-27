package com.example.model.domain.city

import kotlinx.serialization.Serializable

@Serializable
data class CityModelItem(
    val coord: CoordModel,
    val country: String,
    val id: Long,
    val name: String,
    val state: String
)