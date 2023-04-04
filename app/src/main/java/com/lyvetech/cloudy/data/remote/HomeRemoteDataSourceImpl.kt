package com.lyvetech.cloudy.data.remote

import android.os.Build
import androidx.annotation.RequiresApi
import com.lyvetech.cloudy.common.model.LocationModel
import com.lyvetech.cloudy.data.remote.dto.WeatherDto
import com.lyvetech.cloudy.data.remote.retrofit.WeatherApiService
import com.lyvetech.cloudy.di.IoDispatcher
import com.lyvetech.cloudy.domain.util.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeRemoteDataSourceImpl @Inject constructor(
    private val apiService: WeatherApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : HomeRemoteDataSource {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getWeather(location: LocationModel): Resource<WeatherDto> =
        withContext(ioDispatcher) {
            return@withContext try {
                Resource.Success(
                    data = apiService.getWeatherData(
                        lat = location.latitude, long = location.longitude
                    ).body()
                )
            } catch (e: Exception) {
                Resource.Error(null, e.toString())
            }
        }
}