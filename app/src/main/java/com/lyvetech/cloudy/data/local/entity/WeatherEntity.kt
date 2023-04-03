package com.lyvetech.cloudy.data.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "weather_table")
@JsonClass(generateAdapter = true)
data class WeatherEntity(

    @PrimaryKey
    @field:Json(name = "lastFetchTime")
    val lastFetchTime: Long,
    @Embedded
    @field:Json(name = "weatherData")
    val weatherData: WeatherDataEntity,
    @field:Json(name = "latitude")
    val latitude: Double,
    @field:Json(name = "longitude")
    val longitude: Double
)