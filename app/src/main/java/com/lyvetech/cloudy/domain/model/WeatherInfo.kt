package com.lyvetech.cloudy.domain.model

import com.lyvetech.cloudy.common.model.LocationModel

data class WeatherInfo(
    val weatherDataPerDay: Map<Int, List<WeatherData>>,
    val currentWeatherData: WeatherData?,
    val location: LocationModel,
    val lastFetchedTime: Long
)