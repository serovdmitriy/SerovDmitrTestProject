package com.example.database.dataSource

import com.example.model.domain.city.CityModelItem

interface DataBaseDataSource {
    suspend fun getCityList(): List<CityModelItem>
}
