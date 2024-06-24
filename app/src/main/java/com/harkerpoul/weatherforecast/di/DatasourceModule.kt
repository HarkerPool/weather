package com.harkerpoul.weatherforecast.di

import com.harkerpoul.weatherforecast.data.local.AppDatabase
import com.harkerpoul.weatherforecast.data.local.datasource.LocalWeatherDatasource
import com.harkerpoul.weatherforecast.data.local.datasource.LocalWeatherDatasourceImpl
import com.harkerpoul.weatherforecast.data.network.api.WeatherApi
import com.harkerpoul.weatherforecast.data.network.datasource.NetworkWeatherDatasource
import com.harkerpoul.weatherforecast.data.network.datasource.NetworkWeatherDatasourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatasourceModule {

    @Provides
    @Singleton
    fun provideLocalDatasource(
        appDatabase: AppDatabase,
    ): LocalWeatherDatasource {
        return LocalWeatherDatasourceImpl(appDatabase)
    }

    @Provides
    @Singleton
    fun provideNetworkDatasource(
        weatherApi: WeatherApi
    ): NetworkWeatherDatasource {
        return NetworkWeatherDatasourceImpl(weatherApi)
    }
}