package com.example.cloudy.features.weather.data.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import com.squareup.moshi.JsonClass

@Entity(tableName = "weather_table")
@JsonClass(generateAdapter = true)
data class WeatherEntity(
    @Embedded
    val weatherData: WeatherDataEntity
)
