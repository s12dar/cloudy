package com.lyvetech.cloudy.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.lyvetech.cloudy.di.IoDispatcher
import com.lyvetech.cloudy.common.model.LocationModel
import com.lyvetech.cloudy.domain.util.Resource
import com.lyvetech.cloudy.data.local.HomeLocalDataSource
import com.lyvetech.cloudy.data.local.entity.WeatherEntity
import com.lyvetech.cloudy.data.remote.HomeRemoteDataSource
import com.lyvetech.cloudy.data.remote.dto.WeatherDto
import com.lyvetech.cloudy.data.mapper.toWeatherInfo
import com.lyvetech.cloudy.data.mapper.toWeatherLocal
import com.lyvetech.cloudy.domain.model.WeatherInfo
import com.lyvetech.cloudy.domain.repository.HomeRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val remoteDataSource: HomeRemoteDataSource,
    private val localDataSource: HomeLocalDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
//    @ApplicationContext private val appContext: Context
) : HomeRepository {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getWeatherInfo(
        location: LocationModel
    ): Resource<WeatherInfo> =
        withContext(ioDispatcher) {
            fetchWeatherFromLocal()?.takeIf { !it.isExpired() }
                ?.let { Resource.Success(it.toWeatherInfo()) }
                ?: run {
                    when (val result =
                        remoteDataSource.getWeather(location)) {
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