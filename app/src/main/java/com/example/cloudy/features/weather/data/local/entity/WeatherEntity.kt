package com.example.cloudy.features.weather.data.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cloudy.features.weather.domain.model.WeatherData
import com.example.cloudy.features.weather.domain.model.WeatherInfo

@Entity(tableName = "weather_table")
data class WeatherEntity(
    @Embedded
    val weatherData: WeatherDataEntity
)
