package com.harkerpoul.weatherforecast.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.harkerpoul.weatherforecast.data.model.City

@Entity // (tableName = "city")
data class CityEntity(
    @PrimaryKey val id: Int,
    var q: String,
    val name: String,
    val country: String
)

fun CityEntity.toCity() = City(id, q, name, country)