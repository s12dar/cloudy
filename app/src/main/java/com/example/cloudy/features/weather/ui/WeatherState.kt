package com.example.cloudy.features.weather.ui

import com.example.cloudy.features.weather.domain.model.WeatherInfo

data class WeatherState(
    val weatherInfo: WeatherInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
