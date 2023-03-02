package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.database.dao.CityDao
import com.example.database.model.CityEntity

@Database(entities = [CityEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao
}
