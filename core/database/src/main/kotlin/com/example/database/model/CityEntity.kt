package com.example.database.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.model.domain.city.CityModelItem
import com.example.model.domain.city.CoordModel

@SuppressWarnings("DataClassShouldBeImmutable")
@Entity(tableName = "city")
data class CityEntity(
    @PrimaryKey
    val id: Long = 0,
    val name: String? = null,
    var country: String? = null,
    var state: String? = null,
    var imageUrl: String = "",
    @Embedded
    var coord: CoordModel? = null
)

fun CityModelItem.toEntity(url: String) = CityEntity(
    id = id,
    name = name,
    country = country,
    coord = coord,
    imageUrl = url
)
