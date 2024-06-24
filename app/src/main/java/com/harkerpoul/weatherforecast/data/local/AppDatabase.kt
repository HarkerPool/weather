package com.harkerpoul.weatherforecast.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.harkerpoul.weatherforecast.data.local.dao.CityDao
import com.harkerpoul.weatherforecast.data.local.dao.WeatherDao
import com.harkerpoul.weatherforecast.data.local.entities.CityEntity
import com.harkerpoul.weatherforecast.data.local.entities.WeatherEntity

/**
 * The Room Database
 *
 * Note that exportSchema should be true in production databases.
 */
@Database(entities = [CityEntity::class, WeatherEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao
    abstract fun weatherDao(): WeatherDao
}