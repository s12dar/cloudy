package com.lyvetech.cloudy.data.local

import com.lyvetech.cloudy.data.local.entity.WeatherEntity
import com.lyvetech.cloudy.data.local.entity.WeatherForecastEntity

interface HomeLocalDataSource {
    suspend fun insertWeather(weatherEntity: WeatherEntity)
    suspend fun insertWeatherForecast(weatherForecastEntity: WeatherForecastEntity)
    suspend fun getWeather(): WeatherEntity?
    suspend fun getWeatherForecast(): WeatherForecastEntity?
    suspend fun deleteAllWeather()
    suspend fun deleteAllWeatherForecast()
}