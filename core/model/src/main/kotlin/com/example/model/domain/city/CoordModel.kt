package com.example.model.domain.city

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class CoordModel(
    val lat: Double,
    val lon: Double
): Parcelable