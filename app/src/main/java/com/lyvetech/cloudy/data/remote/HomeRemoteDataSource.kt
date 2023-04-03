package com.lyvetech.cloudy.data.remote

import com.lyvetech.cloudy.common.model.LocationModel
import com.lyvetech.cloudy.domain.util.Resource
import com.lyvetech.cloudy.data.remote.dto.WeatherDto

interface HomeRemoteDataSource {
    suspend fun getWeather(location: LocationModel): Resource<WeatherDto>
}