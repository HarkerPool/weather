package com.harkerpoul.weatherforecast.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.harkerpoul.weatherforecast.data.model.Weather

@Entity(tableName = "weathers")
data class WeatherEntity(
    @PrimaryKey val dt: Long = 0,
    @ColumnInfo(name = "city_id") val cityId: Int,
    @ColumnInfo(name = "average_temperature", defaultValue = "0") val averageTemperature: Int,
    @ColumnInfo(defaultValue = "0") val pressure: Int,
    @ColumnInfo(defaultValue = "0") val humidity: Int,
    @ColumnInfo(defaultValue = "") val description: String,
)

fun WeatherEntity.toWeather() = Weather(
    dt = dt,
    averageTemperature = averageTemperature,
    pressure = pressure,
    humidity = humidity,
    description = description
)
