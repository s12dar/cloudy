package com.lyvetech.cloudy.domain.repository

import com.lyvetech.cloudy.common.model.LocationModel
import com.lyvetech.cloudy.domain.util.Resource
import com.lyvetech.cloudy.domain.model.WeatherInfo

interface HomeRepository {
    suspend fun getWeatherInfo(location: LocationModel): Resource<WeatherInfo>
}