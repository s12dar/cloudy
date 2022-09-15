package com.example.cloudy.features.weather.data.repository

import com.example.cloudy.core.di.IoDispatcher
import com.example.cloudy.core.util.Resource
import com.example.cloudy.features.weather.data.local.WeatherLocalDataSource
import com.example.cloudy.features.weather.data.remote.WeatherRemoteDataSource
import com.example.cloudy.features.weather.domain.model.WeatherInfo
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val remoteDataSource: WeatherRemoteDataSource,
    private val localDataSource: WeatherLocalDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): WeatherRepository {
    override suspend fun getWeatherInfo(lat: Double, long: Double): Resource<WeatherInfo> {
        TODO("Not yet implemented")
    }

}