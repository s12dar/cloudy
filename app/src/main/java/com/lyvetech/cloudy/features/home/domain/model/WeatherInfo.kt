package com.lyvetech.cloudy.features.home.domain.model

import com.lyvetech.cloudy.core.domain.model.LocationModel

data class WeatherInfo(
    val weatherDataPerDay: Map<Int, List<WeatherData>>,
    val currentWeatherData: WeatherData?,
    val location: LocationModel,
    val lastFetchedTime: Long
)