package com.example.cloudy.features.home.data.remote.dto

import com.squareup.moshi.Json

data class WeatherDto(
    @field:Json(name = "latitude")
    val latitude: Double,
    @field:Json(name = "longitude")
    val longitude: Double,
    @field:Json(name = "hourly")
    val weatherData: WeatherDataDto
)