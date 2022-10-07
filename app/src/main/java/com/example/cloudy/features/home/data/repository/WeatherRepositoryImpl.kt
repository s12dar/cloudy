package com.example.cloudy.features.home.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.cloudy.core.di.IoDispatcher
import com.example.cloudy.core.util.Resource
import com.example.cloudy.features.home.data.local.WeatherLocalDataSource
import com.example.cloudy.features.home.data.local.entity.WeatherEntity
import com.example.cloudy.features.home.data.mapper.toWeatherInfo
import com.example.cloudy.features.home.data.mapper.toWeatherLocal
import com.example.cloudy.features.home.data.remote.WeatherRemoteDataSource
import com.example.cloudy.features.home.data.remote.dto.WeatherDto
import com.example.cloudy.features.home.domain.model.WeatherInfo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val remoteDataSource: WeatherRemoteDataSource,
    private val localDataSource: WeatherLocalDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : WeatherRepository {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getWeatherInfo(
        lat: Double,
        long: Double
    ): Resource<WeatherInfo> =
        withContext(ioDispatcher) {
            fetchWeatherFromLocal()?.takeIf { !it.isExpired() }
                ?.let { Resource.Success(it.toWeatherInfo()) }
                ?: run {
                    when (val result = remoteDataSource.getWeather(lat, long)) {
                        is Resource.Success -> {
                            localDataSource.deleteAllWeather()
                            result.data?.let {
                                insertWeatherResponse(
                                    it
                                )
                            }
                            val localResult = fetchWeatherFromLocal()?.toWeatherInfo()
                            Resource.Success(localResult)
                        }
                        is Resource.Error -> {
                            val localResult = fetchWeatherFromLocal()?.toWeatherInfo()
                            Resource.Error(localResult, result.message)
                        }
                        else -> {
                            Resource.Loading()
                        }
                    }
                }
        }

    private suspend fun insertWeatherResponse(remoteData: WeatherDto) {
        localDataSource.insertWeather(
            remoteData.toWeatherLocal(
                lastFetchTime = System.currentTimeMillis()
            )
        )
    }

    private suspend fun fetchWeatherFromLocal() =
        localDataSource.getWeather()

    private fun WeatherEntity.isExpired(): Boolean =
        System.currentTimeMillis() - lastFetchTime > EXPIRED_TIME

    companion object {
        private const val EXPIRED_TIME = 1000L * 60
    }
}