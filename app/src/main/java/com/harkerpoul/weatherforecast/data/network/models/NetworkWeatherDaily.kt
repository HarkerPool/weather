package com.harkerpoul.weatherforecast.data.network.models

import com.harkerpoul.weatherforecast.data.local.entities.CityEntity
import com.harkerpoul.weatherforecast.data.local.entities.WeatherEntity
import com.harkerpoul.weatherforecast.data.model.City
import com.harkerpoul.weatherforecast.data.model.Weather

data class NetworkWeatherDaily(
    val city: City? = null,
    val cod: String? = null,
    val message: String? = null,
    val cnt: Int = 0,
    val list: List<Weather> = emptyList()
)

fun NetworkWeatherDaily.toCityEntity(q: String) = CityEntity(
    id = city?.id ?: 0,
    q = q,
    name = city?.name ?: "",
    country = city?.country ?: "",
)

fun NetworkWeatherDaily.toWeatherEntityList(cityId: Int): List<WeatherEntity> {
    return list.map {
        WeatherEntity(
            dt = it.dt,
            cityId = cityId,
            averageTemperature = it.averageTemperature,
            pressure = it.pressure,
            humidity = it.humidity,
            description = it.description
        )
    }
}

fun NetworkWeatherDaily.toWeatherList(): List<Weather> {
    return list.map {
        Weather(
            dt = it.dt,
            averageTemperature = it.averageTemperature,
            pressure = it.pressure,
            humidity = it.humidity,
            description = it.description
        )
    }
}