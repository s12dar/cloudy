package com.example.cloudy.features.weather.data.remote

import com.example.cloudy.core.di.IoDispatcher
import com.example.cloudy.core.util.Resource
import com.example.cloudy.features.weather.data.remote.dto.WeatherDto
import com.example.cloudy.features.weather.data.remote.retrofit.WeatherApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherRemoteDataSourceImpl @Inject constructor(
    private val apiService: WeatherApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : WeatherRemoteDataSource {
    override suspend fun getWeather(lat: Double, long: Double): Resource<WeatherDto> =
        withContext(ioDispatcher) {
            return@withContext try {
                Resource.Success(
                    data = apiService.getWeatherData(
                        lat = lat, long = long
                    ).body()
                )
            } catch (e: Exception) {
                Resource.Error(null, e.toString())
            }
        }
}