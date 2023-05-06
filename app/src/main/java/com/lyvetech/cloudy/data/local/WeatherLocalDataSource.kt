package com.lyvetech.cloudy.data.local

import com.lyvetech.cloudy.data.local.entity.WeatherEntity
import com.lyvetech.cloudy.data.local.entity.WeatherForecastEntity

interface WeatherLocalDataSource {
    suspend fun insertWeather(weatherEntity: WeatherEntity)
    suspend fun insertWeatherForecast(weatherForecastEntity: List<WeatherForecastEntity>)
    suspend fun getWeather(): WeatherEntity?
    suspend fun getWeatherForecast(): List<WeatherForecastEntity>?
    suspend fun deleteAllWeather()
    suspend fun deleteAllWeatherForecast()
}