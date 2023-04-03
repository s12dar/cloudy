package com.lyvetech.cloudy.data.local.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherDataEntity(
    @field:Json(name = "time")
    val time: List<String>,
    @field:Json(name = "temperature")
    val temperatures: List<Double>,
    @field:Json(name = "weatherCodes")
    val weatherCodes: List<Int>,
    @field:Json(name = "pressures")
    val pressures: List<Double>,
    @field:Json(name = "windSpeeds")
    val windSpeeds: List<Double>,
    @field:Json(name = "humidities")
    val humidities: List<Double>
)
