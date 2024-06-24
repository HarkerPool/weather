package com.harkerpoul.weatherforecast.data.local.datasource

import com.harkerpoul.weatherforecast.data.local.AppDatabase
import com.harkerpoul.weatherforecast.data.local.entities.CityEntity
import com.harkerpoul.weatherforecast.data.local.entities.WeatherEntity
import com.harkerpoul.weatherforecast.data.local.entities.toWeather
import com.harkerpoul.weatherforecast.data.model.Weather
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

class LocalWeatherDatasourceImpl @Inject constructor(
    private val appDatabase: AppDatabase,
) : LocalWeatherDatasource {
    override suspend fun getWeatherForecastDaily(
        cityName: String,
        numberOfDays: Int
    ): List<Weather>? {
        try {
            val cityEntity = appDatabase.cityDao().getCityByName(cityName)

            if (cityEntity == null || cityEntity.id == 0) {
                return null
            } else {
                val weatherList =
                    appDatabase.weatherDao().getWeatherByCityId(cityEntity.id, numberOfDays)

                if (weatherList.isNullOrEmpty()) {
                    return null
                } else {
                    return weatherList.map {
                        it.toWeather()
                    }
                }
            }
        } catch (e: Exception) {
            throw Exception(e)
        }
    }

    override suspend fun saveWeatherIntoDB(
        cityEntity: CityEntity,
        weatherEntityList: List<WeatherEntity>
    ) {
        try {
            val mutex = Mutex()
            mutex.withLock {
                appDatabase.cityDao().insert(cityEntity)
                appDatabase.weatherDao().insertAll(weatherEntityList)
            }
        } catch (e: Exception) {
            throw Exception(e)
        }
    }
}