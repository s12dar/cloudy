package com.example.cloudy.features.weather.data.local

import com.example.cloudy.features.weather.data.local.entity.WeatherEntity
import javax.inject.Inject

class WeatherLocalDataSourceImpl @Inject constructor(
) : WeatherLocalDataSource {
    override suspend fun insertWeather(weather: WeatherEntity) {
        TODO("Not yet implemented")
    }
}