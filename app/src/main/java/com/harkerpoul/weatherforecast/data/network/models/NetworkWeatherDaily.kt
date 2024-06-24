package com.harkerpoul.weatherforecast.data.network.models

import com.harkerpoul.weatherforecast.data.model.City
import com.harkerpoul.weatherforecast.data.model.Weather

data class NetworkWeatherDaily(
    val city: City? = null,
    val cod: String? = null,
    val message: String? = null,
    val cnt: Int? = null,
    val list: List<Weather>? = emptyList()
)
