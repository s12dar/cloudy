package com.example.cloudy.features.home.domain.model

data class WeatherInfo(
    val weatherDataPerDay: Map<Int, List<WeatherData>>,
    val currentWeatherData: WeatherData?,
    val location: String,
    val lastFetchedTime: Long
)