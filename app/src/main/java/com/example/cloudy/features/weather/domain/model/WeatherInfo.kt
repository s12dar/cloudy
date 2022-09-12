package com.example.cloudy.features.weather.domain.model

data class WeatherInfo(
    val weatherInfoPerDay: Map<Int, List<WeatherData>>,
    val currentWeatherData: WeatherData?
)