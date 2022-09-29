package com.example.cloudy.features.home.data.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "weather_table")
@JsonClass(generateAdapter = true)
data class WeatherEntity(

    @PrimaryKey
    @field:Json(name = "timespan")
    val timeSpan: String,
    @field:Json(name = "lastFetchTime")
    val lastFetchTime: Long,
    @Embedded
    @field:Json(name = "weatherData")
    val weatherData: WeatherDataEntity
)
