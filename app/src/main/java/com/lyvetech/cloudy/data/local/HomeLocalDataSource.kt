package com.lyvetech.cloudy.data.local

import com.lyvetech.cloudy.data.local.entity.WeatherEntity

interface HomeLocalDataSource {
    suspend fun insertWeather(weather: WeatherEntity)
    suspend fun getWeather(): WeatherEntity?
    suspend fun deleteAllWeather()
}