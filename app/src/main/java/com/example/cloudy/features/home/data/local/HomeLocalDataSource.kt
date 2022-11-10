package com.example.cloudy.features.home.data.local

import com.example.cloudy.features.home.data.local.entity.WeatherEntity

interface HomeLocalDataSource {
    suspend fun insertWeather(weather: WeatherEntity)
    suspend fun getWeather(): WeatherEntity?
    suspend fun deleteAllWeather()
}