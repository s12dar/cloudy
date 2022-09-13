package com.example.cloudy.features.weather.data.local

import com.example.cloudy.core.util.Resource
import com.example.cloudy.features.weather.domain.model.WeatherInfo

interface WeatherLocalDataSource {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>
}