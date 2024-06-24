package com.harkerpoul.weatherforecast.data.repository

import com.harkerpoul.weatherforecast.data.model.Weather

interface WeatherRepository {
    suspend fun getWeatherForecastDaily(cityName: String, numberOfDays: Int): List<Weather>?
}