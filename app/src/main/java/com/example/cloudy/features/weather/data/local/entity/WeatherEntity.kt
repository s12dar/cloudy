package com.example.cloudy.features.weather.data.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "weather_table")
@JsonClass(generateAdapter = true)
data class WeatherEntity(

    @PrimaryKey(autoGenerate = true)
    @field:Json(name = "uid")
    val uid: Int,

    @Embedded
    @field:Json(name = "weatherData")
    val weatherData: WeatherDataEntity
)
