package com.harkerpoul.weatherforecast.data.repository

import com.harkerpoul.weatherforecast.data.local.datasource.LocalWeatherDatasource
import com.harkerpoul.weatherforecast.data.network.datasource.NetworkWeatherDatasource
import com.harkerpoul.weatherforecast.data.network.models.NetworkWeatherDaily
import com.harkerpoul.weatherforecast.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val dataSource: NetworkWeatherDatasource,
    private val localDataSource: LocalWeatherDatasource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : WeatherRepository {
    override suspend fun getWeatherForecastDaily(
        cityName: String,
        numberOfDays: Int
    ): NetworkWeatherDaily? {
        // Get data from DB
        // Get data from API
        val response = dataSource.getWeatherForecastDaily(cityName, numberOfDays)
        if (response != null && response.cod == "200") {
            // Save data to DB
//            localDataSource.saveWeatherForecastDaily(response)
        }
        return null
    }
}