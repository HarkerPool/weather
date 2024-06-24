package com.harkerpoul.weatherforecast.data.network.api

import com.harkerpoul.weatherforecast.data.network.models.NetworkWeatherDaily
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("data/2.5/forecast/daily")
    suspend fun getWeatherForecastDaily(
        @Query("q") cityName: String,
        @Query("units") units: String,
        @Query("cnt") numberOfDays: Int,
        @Query("appid") apiKey: String = "60c6fbeb4b93ac653c492ba806fc346d", // should define in the .properties file
    ): Response<NetworkWeatherDaily>
}