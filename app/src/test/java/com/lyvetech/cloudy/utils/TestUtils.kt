package com.lyvetech.cloudy.utils

import com.lyvetech.cloudy.common.model.LocationModel
import com.lyvetech.cloudy.common.model.WeatherCondition
import com.lyvetech.cloudy.common.model.WeatherDescription
import com.lyvetech.cloudy.common.model.Wind
import com.lyvetech.cloudy.data.local.entity.WeatherEntity
import com.lyvetech.cloudy.data.mapper.transformEntToDomain
import com.lyvetech.cloudy.data.remote.dto.WeatherDto


val fakeNotExpiredWeatherEntity = WeatherEntity(
    1,
    lastFetchTime = System.currentTimeMillis(),
    123,
    "Lagos",
    Wind(32.5, 24),
    listOf(WeatherDescription(1L, "Main", "Cloudy", "icon")),
    WeatherCondition(324.43, 1234.32, 32.5)
)

val fakeExpiredWeatherEntity = WeatherEntity(
    1,
    lastFetchTime = System.currentTimeMillis() - 60 * 5000L,
    123,
    "Lagos",
    Wind(32.5, 24),
    listOf(WeatherDescription(1L, "Main", "Cloudy", "icon")),
    WeatherCondition(324.43, 1234.32, 32.5)
)

val fakeWeatherDto = WeatherDto(
    1,
    123,
    "Lagos",
    Wind(32.5, 24),
    listOf(WeatherDescription(1L, "Main", "Cloudy", "icon")),
    WeatherCondition(324.43, 1234.32, 32.5)
)

val fakeNotExpiredWeatherInfo = fakeNotExpiredWeatherEntity.transformEntToDomain()

val fakeExpiredWeatherInfo = fakeExpiredWeatherEntity.transformEntToDomain()

val dummyLocation = LocationModel(
    latitude = 12.0,
    longitude = 12.0
)