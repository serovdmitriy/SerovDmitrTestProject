package com.example.database.dataSource

import android.content.res.AssetManager
import com.example.model.domain.city.CityModelItem
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import javax.inject.Inject

@OptIn(ExperimentalSerializationApi::class)
class DataBaseDataSourceImpl @Inject constructor(
    private val json: Json,
    private val assets: AssetManager
) : DataBaseDataSource {

    override suspend fun getCityList(): List<CityModelItem> {
        return assets.open(CITY_LIST).use(json::decodeFromStream)
    }

    companion object {
        private const val CITY_LIST = "city_list.json"
    }
}
