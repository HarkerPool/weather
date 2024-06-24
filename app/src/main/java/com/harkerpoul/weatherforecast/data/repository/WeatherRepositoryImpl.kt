package com.harkerpoul.weatherforecast.data.repository

import com.harkerpoul.weatherforecast.data.local.datasource.LocalWeatherDatasource
import com.harkerpoul.weatherforecast.data.model.Weather
import com.harkerpoul.weatherforecast.data.network.datasource.NetworkWeatherDatasource
import com.harkerpoul.weatherforecast.data.network.models.toCityEntity
import com.harkerpoul.weatherforecast.data.network.models.toWeatherEntityList
import com.harkerpoul.weatherforecast.data.network.models.toWeatherList
import com.harkerpoul.weatherforecast.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val networkDatasource: NetworkWeatherDatasource,
    private val localDatasource: LocalWeatherDatasource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : WeatherRepository {
    override suspend fun getWeatherForecastDaily(
        cityName: String,
        units: String,
        numberOfDays: Int
    ): List<Weather>? {
        return withContext(dispatcher) {
            try {
                // Get data from DB
                val dbValues =
                    localDatasource.getWeatherForecastDaily(cityName, numberOfDays)
                if (!dbValues.isNullOrEmpty()) {
                    return@withContext dbValues
                }

                // Get data from API
                val networkValues =
                    networkDatasource.getWeatherForecastDaily(cityName, units, numberOfDays)
                if (networkValues != null && networkValues.cod == "200") {
                    val cityEntity = networkValues.toCityEntity(cityName)
                    val weatherEntityList = networkValues.toWeatherEntityList(cityEntity.id)
                    // Save data to DB
                    launch {
                        localDatasource.saveWeatherIntoDB(cityEntity, weatherEntityList)
                    }

                    return@withContext networkValues.toWeatherList()
                }

                return@withContext null
            } catch (e: Exception) {
                throw Exception(e)
            }
        }
    }
}