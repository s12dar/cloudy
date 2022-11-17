package com.example.cloudy.features.home.data.repository

import com.example.cloudy.core.domain.model.LocationModel
import com.example.cloudy.core.util.Resource
import com.example.cloudy.features.home.domain.model.WeatherInfo

interface HomeRepository {
    suspend fun getWeatherInfo(location: LocationModel): Resource<WeatherInfo>
}