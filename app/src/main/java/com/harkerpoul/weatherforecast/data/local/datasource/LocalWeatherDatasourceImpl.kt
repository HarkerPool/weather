package com.harkerpoul.weatherforecast.data.local.datasource

import com.harkerpoul.weatherforecast.data.local.AppDatabase
import javax.inject.Inject

class LocalWeatherDatasourceImpl @Inject constructor(
    private val appDatabase: AppDatabase,
): LocalWeatherDatasource
{
    override suspend fun getWeatherForecastDaily() {
        TODO("Not yet implemented")
    }
}