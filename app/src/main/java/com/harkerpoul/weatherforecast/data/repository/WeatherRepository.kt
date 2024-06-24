package com.harkerpoul.weatherforecast.data.repository

import com.harkerpoul.weatherforecast.data.network.models.NetworkWeatherDaily

interface WeatherRepository {
    suspend fun getWeatherForecastDaily(cityName: String, numberOfDays: Int): NetworkWeatherDaily?
}