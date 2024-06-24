package com.harkerpoul.weatherforecast.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.harkerpoul.weatherforecast.data.model.City

@Entity // (tableName = "city")
data class CityEntity(
    @PrimaryKey val id: Int = 0,
    @ColumnInfo(defaultValue = "") var q: String,
    @ColumnInfo(defaultValue = "") val name: String,
    @ColumnInfo(defaultValue = "") val country: String
)

fun CityEntity.toCity() = City(id, q, name, country)