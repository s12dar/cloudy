package com.lyvetech.cloudy.domain.repository

import com.lyvetech.cloudy.common.model.LocationModel
import com.lyvetech.cloudy.domain.model.Weather
import com.lyvetech.cloudy.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun getWeather(location: LocationModel): Flow<Resource<Weather>>
}