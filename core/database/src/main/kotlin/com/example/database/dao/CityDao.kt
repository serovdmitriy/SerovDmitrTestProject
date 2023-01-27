package com.example.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.model.CityEntity

@Dao
interface CityDao {
    @Query(
        "SELECT * FROM city WHERE name LIKE :name || '%' " +
            "ORDER BY CASE WHEN :name != ''" +
            " THEN :name ELSE id END ASC LIMIT :limit OFFSET :offset"
    )
    suspend fun getPagedListWithSearch(name: String, limit: Int, offset: Int): List<CityEntity>

    @Query("SELECT COUNT(*) FROM city")
    suspend fun citySize(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg cities: CityEntity?)

    @Insert
    suspend fun insertCity(city: CityEntity?)

    @Delete
    suspend fun deleteCity(city: CityEntity?)

    @Query("DELETE FROM city")
    suspend fun deleteCities()
}
