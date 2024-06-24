package com.harkerpoul.weatherforecast.data.local.datasource

interface LocalWeatherDatasource {
    suspend fun getWeatherForecastDaily()
}