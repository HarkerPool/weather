package com.harkerpoul.weatherforecast.data.model

data class Weather(
    val dt: Long = 0,
    val temp: Temperature,
    val pressure: Int = 0,
    val humidity: Int = 0,
    val weather: List<WeatherDescription> = emptyList(),
)
