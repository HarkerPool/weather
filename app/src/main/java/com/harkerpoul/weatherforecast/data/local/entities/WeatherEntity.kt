package com.harkerpoul.weatherforecast.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.harkerpoul.weatherforecast.data.model.Temperature
import com.harkerpoul.weatherforecast.data.model.Weather
import com.harkerpoul.weatherforecast.data.model.WeatherDescription

@Entity(tableName = "weathers")
data class WeatherEntity(
    @PrimaryKey val dt: Long = 0,
    @ColumnInfo(name = "city_id") val cityId: Int,
    @ColumnInfo(name = "temp_min", defaultValue = "0") val tempMin: Int,
    @ColumnInfo(name = "temp_max", defaultValue = "0") val tempMax: Int,
    @ColumnInfo(defaultValue = "0") val pressure: Int,
    @ColumnInfo(defaultValue = "0") val humidity: Int,
    @ColumnInfo(defaultValue = "") val description: String,
)

fun WeatherEntity.toWeather() = Weather(
    dt = dt,
    temp = Temperature(min = tempMin.toDouble(), max = tempMax.toDouble()),
    pressure = pressure,
    humidity = humidity,
    weather = mutableListOf(WeatherDescription(description = description))
)
