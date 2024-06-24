package com.harkerpoul.weatherforecast.di

import com.harkerpoul.weatherforecast.data.local.datasource.LocalWeatherDatasource
import com.harkerpoul.weatherforecast.data.network.datasource.NetworkWeatherDatasource
import com.harkerpoul.weatherforecast.data.repository.WeatherRepository
import com.harkerpoul.weatherforecast.data.repository.WeatherRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(
        networkDatasource: NetworkWeatherDatasource,
        localDatasource: LocalWeatherDatasource
    ): WeatherRepository {
        return WeatherRepositoryImpl(networkDatasource, localDatasource)
    }
}