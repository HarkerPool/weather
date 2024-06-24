package com.harkerpoul.weatherforecast.data.model

data class Weather(
    val dt: Long = 0,
    val averageTemperature: Int = 0,
    val pressure: Int = 0,
    val humidity: Int = 0,
    val description: String = "",
)
