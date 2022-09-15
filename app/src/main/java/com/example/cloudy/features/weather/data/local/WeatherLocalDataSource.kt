package com.example.cloudy.features.weather.data.local

import com.example.cloudy.features.weather.data.local.entity.WeatherEntity

interface WeatherLocalDataSource {
    suspend fun insertWeather(weather: WeatherEntity)
}