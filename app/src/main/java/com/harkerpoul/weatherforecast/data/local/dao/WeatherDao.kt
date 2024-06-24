package com.harkerpoul.weatherforecast.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.harkerpoul.weatherforecast.data.local.entities.WeatherEntity

@Dao
interface WeatherDao {
    @Query("SELECT * FROM weathers WHERE city_id = :cityId LIMIT :cnt")
    fun getWeatherByCityId(cityId: Int, cnt: Int): List<WeatherEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(weatherEntityList: List<WeatherEntity>)
}