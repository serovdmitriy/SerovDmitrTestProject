package com.example.citylist.data.repository

import com.example.citylist.domain.repository.CityListRepository
import com.example.database.dao.CityDao
import com.example.database.dataSource.DataBaseDataSource
import com.example.database.model.toEntity
import javax.inject.Inject

internal class CityListRepositoryImpl @Inject constructor(
    private val dataBaseDataSource: DataBaseDataSource,
    private val cityDao: CityDao
) : CityListRepository {

    @SuppressWarnings("SpreadOperator")
    override suspend fun loadingCityList() {
        if (cityDao.citySize() == 0) {
            val urlTemp1 = "https://infotech.gov.ua/storage/img/Temp1.png"
            val urlTemp3 = "https://infotech.gov.ua/storage/img/Temp3.png"

            val cityList = dataBaseDataSource.getCityList().mapIndexed { index, cityModelItem ->
                val url = if (index % 2 == 0) urlTemp3 else urlTemp1
                cityModelItem.toEntity(url)
            }
            cityDao.insertAll(*cityList.toTypedArray())
        }
    }
}
