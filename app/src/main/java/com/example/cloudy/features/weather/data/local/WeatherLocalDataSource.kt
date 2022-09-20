package com.example.cloudy.features.weather.data.local

import com.example.cloudy.features.weather.data.local.entity.WeatherEntity

interface WeatherLocalDataSource {
    suspend fun insertWeather(weather: WeatherEntity)
    suspend fun getWeather(): WeatherEntity
    suspend fun deleteAllWeather()
}