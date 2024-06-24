package com.harkerpoul.weatherforecast.data.network.datasource

import com.harkerpoul.weatherforecast.data.network.api.WeatherApi
import com.harkerpoul.weatherforecast.data.network.models.NetworkWeatherDaily
import javax.inject.Inject

class NetworkWeatherDatasourceImpl @Inject constructor(
    private val api: WeatherApi
) : NetworkWeatherDatasource {
    override suspend fun getWeatherForecastDaily(
        cityName: String,
        numberOfDays: Int
    ): NetworkWeatherDaily? {
        val response = api.getWeatherForecastDaily(cityName, numberOfDays)

        if (response.isSuccessful) {
            return response.body()
        } else {
            throw Exception("Failed to fetch weather forecast data")
        }
    }
}