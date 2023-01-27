package com.example.citylist.domain.repository

interface CityListRepository {
    suspend fun loadingCityList()
}
