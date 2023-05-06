package com.lyvetech.cloudy.domain.repository

import com.lyvetech.cloudy.common.model.LocationModel
import com.lyvetech.cloudy.domain.model.Weather
import com.lyvetech.cloudy.domain.model.WeatherForecast
import com.lyvetech.cloudy.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getWeather(location: LocationModel): Flow<Resource<Weather>>
    suspend fun getWeatherForecast(cityId: Int): Flow<Resource<List<WeatherForecast>>>
}