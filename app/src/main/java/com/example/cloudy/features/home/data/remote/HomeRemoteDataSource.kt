package com.example.cloudy.features.home.data.remote

import com.example.cloudy.core.domain.model.LocationModel
import com.example.cloudy.core.util.Resource
import com.example.cloudy.features.home.data.remote.dto.WeatherDto

interface HomeRemoteDataSource {
    suspend fun getWeather(location: LocationModel): Resource<WeatherDto>
}