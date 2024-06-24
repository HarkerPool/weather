package com.harkerpoul.weatherforecast.data.model

data class City(
    val id: Int = 0,
    val q: String = "",
    val name: String = "",
//    val coord: Coordinates,
    val country: String = "",
    val population: Int = 0,
    val timezone: Int = 0,
)
