package com.harkerpoul.weatherforecast.data.local.datasource

import com.harkerpoul.weatherforecast.data.local.entities.CityEntity
import com.harkerpoul.weatherforecast.data.local.entities.WeatherEntity
import com.harkerpoul.weatherforecast.data.model.Weather

interface LocalWeatherDatasource {
    suspend fun getWeatherForecastDaily(cityName: String, numberOfDays: Int): List<Weather>?

    suspend fun saveWeatherIntoDB(cityEntity: CityEntity, weatherEntityList: List<WeatherEntity>)

}