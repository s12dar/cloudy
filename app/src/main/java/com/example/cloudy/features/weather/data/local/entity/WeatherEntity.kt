package com.example.cloudy.features.weather.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_table")
data class WeatherEntity(

    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0,
    val time: List<String>,
    val temperatures: List<Double>,
    val weatherCodes: List<Int>,
    val pressures: List<Double>,
    val windSpeeds: List<Double>,
    val humidities: List<Double>
)
