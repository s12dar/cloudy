package com.lyvetech.cloudy.features.home.data.local

import com.lyvetech.cloudy.features.home.data.local.entity.WeatherEntity

interface HomeLocalDataSource {
    suspend fun insertWeather(weather: WeatherEntity)
    suspend fun getWeather(): WeatherEntity?
    suspend fun deleteAllWeather()
}