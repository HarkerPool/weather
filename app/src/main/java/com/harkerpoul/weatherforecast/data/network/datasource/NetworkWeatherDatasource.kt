package com.harkerpoul.weatherforecast.data.network.datasource

import com.harkerpoul.weatherforecast.data.network.models.NetworkWeatherDaily

interface NetworkWeatherDatasource {
    suspend fun getWeatherForecastDaily(cityName: String, numberOfDays: Int): NetworkWeatherDaily?
}