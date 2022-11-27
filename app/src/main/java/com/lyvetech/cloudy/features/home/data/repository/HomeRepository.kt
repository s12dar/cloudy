package com.lyvetech.cloudy.features.home.data.repository

import com.lyvetech.cloudy.core.domain.model.LocationModel
import com.lyvetech.cloudy.core.util.Resource
import com.lyvetech.cloudy.features.home.domain.model.WeatherInfo

interface HomeRepository {
    suspend fun getWeatherInfo(location: LocationModel): Resource<WeatherInfo>
}