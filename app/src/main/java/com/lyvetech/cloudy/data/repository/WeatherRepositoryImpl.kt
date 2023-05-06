package com.lyvetech.cloudy.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.lyvetech.cloudy.common.model.LocationModel
import com.lyvetech.cloudy.data.local.WeatherLocalDataSource
import com.lyvetech.cloudy.data.local.entity.WeatherEntity
import com.lyvetech.cloudy.data.local.entity.WeatherForecastEntity
import com.lyvetech.cloudy.data.mapper.transformDtoToEnt
import com.lyvetech.cloudy.data.mapper.transformDtoToEntity
import com.lyvetech.cloudy.data.mapper.transformEntToDomain
import com.lyvetech.cloudy.data.remote.WeatherRemoteDataSource
import com.lyvetech.cloudy.data.remote.dto.WeatherDto
import com.lyvetech.cloudy.data.remote.dto.WeatherForecastDto
import com.lyvetech.cloudy.domain.model.Weather
import com.lyvetech.cloudy.domain.model.WeatherForecast
import com.lyvetech.cloudy.domain.repository.WeatherRepository
import com.lyvetech.cloudy.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val remoteDataSource: WeatherRemoteDataSource,
    private val localDataSource: WeatherLocalDataSource,
) : WeatherRepository {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getWeather(
        location: LocationModel
    ): Flow<Resource<Weather>> =
        flow {
            fetchWeatherFromLocal()?.takeIf { !it.isExpired() }
                ?.let { emit(Resource.Success(it.transformEntToDomain())) }
                ?: run {
                    when (val result =
                        remoteDataSource.getWeather(location)) {
                        is Resource.Success -> {
                            localDataSource.deleteAllWeather()
                            result.data?.let {
                                insertWeatherResponse(it)
                            }
                            val localResult = fetchWeatherFromLocal()?.transformEntToDomain()
                            emit(Resource.Success(localResult))
                        }

                        is Resource.Error -> {
                            val localResult = fetchWeatherFromLocal()?.transformEntToDomain()
                            Resource.Error(localResult, result.message)
                        }

                        else -> {
                            emit(Resource.Loading())
                        }
                    }
                }
        }

    override suspend fun getWeatherForecast(cityId: Int): Flow<Resource<List<WeatherForecast>>> =
        flow {
            fetchWeatherForecastFromLocal()?.takeIf { it.isNotEmpty() && !it.isExpired() }
                ?.let { emit(Resource.Success(it.transformEntToDomain())) }
                ?: run {
                    when (val result =
                        remoteDataSource.getWeatherForecast(cityId)) {
                        is Resource.Success -> {
                            localDataSource.deleteAllWeatherForecast()
                            result.data?.let {
                                insertWeatherForecastResponse(it)
                            }

                            val localResult =
                                fetchWeatherForecastFromLocal()?.transformEntToDomain()
                            emit(Resource.Success(localResult))
                        }

                        is Resource.Error -> {
                            val localResult =
                                fetchWeatherForecastFromLocal()?.transformEntToDomain()
                            Resource.Error(localResult, result.message)
                        }

                        else -> {
                            emit(Resource.Loading())
                        }
                    }
                }
        }

    private suspend fun insertWeatherResponse(remoteData: WeatherDto) {
        localDataSource.insertWeather(
            remoteData.transformDtoToEnt(
                lastFetchTime = System.currentTimeMillis()
            )
        )
    }

    private suspend fun insertWeatherForecastResponse(remoteData: List<WeatherForecastDto>) {
        localDataSource.insertWeatherForecast(
            remoteData.transformDtoToEntity(
                lastFetchTime = System.currentTimeMillis()
            )
        )
    }

    private suspend fun fetchWeatherFromLocal(): WeatherEntity? =
        localDataSource.getWeather()

    private suspend fun fetchWeatherForecastFromLocal(): List<WeatherForecastEntity>? =
        localDataSource.getWeatherForecast()

    private fun WeatherEntity.isExpired(): Boolean =
        System.currentTimeMillis() - lastFetchTime > EXPIRED_TIME

    private fun List<WeatherForecastEntity>.isExpired(): Boolean =
        System.currentTimeMillis() - this.first().lastFetchTime > EXPIRED_TIME

    companion object {
        private const val EXPIRED_TIME = 1000L * 60
    }
}