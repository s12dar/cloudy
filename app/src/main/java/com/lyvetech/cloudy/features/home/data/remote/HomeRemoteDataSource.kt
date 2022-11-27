package com.lyvetech.cloudy.features.home.data.remote

import com.lyvetech.cloudy.core.domain.model.LocationModel
import com.lyvetech.cloudy.core.util.Resource
import com.lyvetech.cloudy.features.home.data.remote.dto.WeatherDto

interface HomeRemoteDataSource {
    suspend fun getWeather(location: LocationModel): Resource<WeatherDto>
}