package com.example.cloudy.features.home.data.repository

import com.example.cloudy.core.util.Resource
import com.example.cloudy.features.home.domain.model.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherInfo(lat: Double, long: Double, timeSpan: String): Resource<WeatherInfo>
}