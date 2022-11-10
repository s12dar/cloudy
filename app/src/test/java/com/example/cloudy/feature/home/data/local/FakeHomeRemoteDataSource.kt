package com.example.cloudy.feature.home.data.local

import com.example.cloudy.core.util.Resource
import com.example.cloudy.features.home.data.remote.HomeRemoteDataSource
import com.example.cloudy.features.home.data.remote.dto.WeatherDto

class FakeHomeRemoteDataSource(
    var weathers: List<WeatherDto> = mutableListOf()
) : HomeRemoteDataSource {
    override suspend fun getWeather(lat: Double, long: Double): Resource<WeatherDto> {
        TODO("Not yet implemented")
    }
}