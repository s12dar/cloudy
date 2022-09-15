package com.example.cloudy.features.weather.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherDto(
    @field:Json(name = "hourly")
    val weatherData: WeatherDataDto
)
