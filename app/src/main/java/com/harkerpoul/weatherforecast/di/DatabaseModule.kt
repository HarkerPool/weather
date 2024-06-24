package com.harkerpoul.weatherforecast.di

import android.content.Context
import androidx.room.Room
import com.harkerpoul.weatherforecast.data.local.AppDatabase
import com.harkerpoul.weatherforecast.data.local.dao.CityDao
import com.harkerpoul.weatherforecast.data.local.dao.WeatherDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "my_database.db"
        ).build()
    }

    @Provides
    fun provideCityDao(database: AppDatabase): CityDao = database.cityDao()

    @Provides
    fun provideWeatherDao(database: AppDatabase): WeatherDao = database.weatherDao()
}