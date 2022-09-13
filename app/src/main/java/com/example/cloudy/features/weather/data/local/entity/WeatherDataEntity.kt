package com.example.cloudy.features.weather.data.local.entity

import com.squareup.moshi.Json

data class WeatherDataEntity(
    val time: List<String>,
    val temperatures: List<Double>,
    val weatherCodes: List<Int>,
    val pressures: List<Double>,
    val windSpeeds: List<Double>,
    val humidities: List<Double>
)
