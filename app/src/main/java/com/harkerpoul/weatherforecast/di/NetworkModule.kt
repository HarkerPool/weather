package com.harkerpoul.weatherforecast.di

import com.harkerpoul.weatherforecast.common.AppConfigs.BASE_URL
import com.harkerpoul.weatherforecast.data.network.api.WeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitClient(baseUrl: String, httpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(debug: Boolean): OkHttpClient {
        val builder = OkHttpClient.Builder()

        if (debug) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(logging)
        }

        return builder.build()
    }

    @Provides
    fun provideWeatherApi(): WeatherApi {
//        return provideRetrofitClient(BASE_URL, provideOkHttpClient(BuildConfig.DEBUG)).create(WeatherApi::class.java)
        return provideRetrofitClient(
            BASE_URL,
            provideOkHttpClient(debug = true)
        ).create(WeatherApi::class.java)
    }
}