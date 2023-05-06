package com.lyvetech.cloudy.data.remote

import com.lyvetech.cloudy.common.model.LocationModel
import com.lyvetech.cloudy.data.remote.dto.WeatherDto
import com.lyvetech.cloudy.data.remote.dto.WeatherForecastDto
import com.lyvetech.cloudy.domain.util.Resource

interface WeatherRemoteDataSource {
    suspend fun getWeather(location: LocationModel): Resource<WeatherDto>
    suspend fun getWeatherForecast(cityId: Int): Resource<List<WeatherForecastDto>>
}