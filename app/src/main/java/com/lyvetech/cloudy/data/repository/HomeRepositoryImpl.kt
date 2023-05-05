package com.lyvetech.cloudy.data.repository

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.lyvetech.cloudy.common.model.LocationModel
import com.lyvetech.cloudy.data.local.HomeLocalDataSource
import com.lyvetech.cloudy.data.local.entity.WeatherEntity
import com.lyvetech.cloudy.data.mapper.transformDtoToEnt
import com.lyvetech.cloudy.data.mapper.transformEntToDomain
import com.lyvetech.cloudy.data.remote.HomeRemoteDataSource
import com.lyvetech.cloudy.data.remote.dto.WeatherDto
import com.lyvetech.cloudy.domain.model.Weather
import com.lyvetech.cloudy.domain.repository.HomeRepository
import com.lyvetech.cloudy.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val remoteDataSource: HomeRemoteDataSource,
    private val localDataSource: HomeLocalDataSource,
) : HomeRepository {
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
                            Log.i("hi Serdar", result.message.toString())
                            val localResult = fetchWeatherFromLocal()?.transformEntToDomain()
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

    private suspend fun fetchWeatherFromLocal() =
        localDataSource.getWeather()

    private fun WeatherEntity.isExpired(): Boolean =
        System.currentTimeMillis() - lastFetchTime > EXPIRED_TIME

    companion object {
        private const val EXPIRED_TIME = 1000L * 60
    }
}