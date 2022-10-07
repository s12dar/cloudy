package com.example.cloudy.features.home.ui

import com.example.cloudy.features.home.domain.model.WeatherInfo

data class HomeScreenState(
    val weatherInfo: WeatherInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)