package com.harkerpoul.weatherforecast.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.harkerpoul.weatherforecast.data.local.entities.CityEntity

@Dao
interface CityDao {
    @Query("SELECT * FROM CityEntity WHERE q LIKE :q LIMIT 1")
    fun getCityByName(q: String): CityEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(city: CityEntity)
}