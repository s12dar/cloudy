package com.example.cloudy.utils

import com.example.cloudy.core.domain.model.LocationModel
import com.example.cloudy.features.home.data.local.entity.WeatherDataEntity
import com.example.cloudy.features.home.data.local.entity.WeatherEntity
import com.example.cloudy.features.home.data.mapper.toWeatherInfo
import com.example.cloudy.features.home.data.remote.dto.WeatherDataDto
import com.example.cloudy.features.home.data.remote.dto.WeatherDto
import com.example.cloudy.features.home.domain.model.WeatherData
import com.example.cloudy.features.home.domain.model.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


val fakeWeatherDataDto = WeatherDataDto(
    time = listOf("01:00", "02:00", "03:00"),
    temperatures = listOf(1.0, 2.0, 3.0),
    weatherCodes = listOf(1, 2, 3),
    pressures = listOf(1.0, 2.0, 3.0),
    windSpeeds = listOf(1.0, 2.0, 3.0),
    humidities = listOf(1.0, 2.0, 3.0)
)

val fakeWeatherData = WeatherData(
    time = LocalDateTime.parse("2022-02-22T02:05:58.147Z", DateTimeFormatter.ISO_DATE_TIME),
    temperatureCelsius = 1.0,
    pressure = 1.0,
    windSpeed = 1.0,
    humidity = 1.0,
    weatherType = WeatherType.ClearSky
)

val fakeWeatherDataEntity = WeatherDataEntity(
    time = listOf(
        "2022-02-22T02:05:58.147Z",
        "2022-02-22T02:06:58.147Z",
        "2022-02-22T02:07:58.147Z"
    ),
    temperatures = listOf(1.0, 2.0, 3.0),
    weatherCodes = listOf(0, 1, 3),
    pressures = listOf(1.0, 2.0, 3.0),
    windSpeeds = listOf(1.0, 2.0, 3.0),
    humidities = listOf(1.0, 2.0, 3.0)
)

val fakeNotExpiredWeatherEntity = WeatherEntity(
    lastFetchTime = System.currentTimeMillis(),
    weatherData = fakeWeatherDataEntity,
    latitude = 12.0,
    longitude = 12.0
)

val fakeNotExpiredWeatherInfo = fakeNotExpiredWeatherEntity.toWeatherInfo()


val fakeWeatherDto = WeatherDto(
    latitude = 12.0,
    longitude = 12.0,
    weatherData = fakeWeatherDataDto
)

val dummyLocation = LocationModel(
    latitude = 12.0,
    longitude = 12.0
)
