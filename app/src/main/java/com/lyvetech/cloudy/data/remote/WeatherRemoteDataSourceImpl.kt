package com.lyvetech.cloudy.data.remote

import android.os.Build
import androidx.annotation.RequiresApi
import com.lyvetech.cloudy.common.model.LocationModel
import com.lyvetech.cloudy.data.remote.dto.WeatherDto
import com.lyvetech.cloudy.data.remote.dto.WeatherForecastDto
import com.lyvetech.cloudy.data.remote.retrofit.WeatherApiService
import com.lyvetech.cloudy.di.IoDispatcher
import com.lyvetech.cloudy.domain.util.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherRemoteDataSourceImpl @Inject constructor(
    private val apiService: WeatherApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : WeatherRemoteDataSource {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getWeather(location: LocationModel): Resource<WeatherDto> =
        withContext(ioDispatcher) {
            return@withContext try {
                Resource.Success(
                    data = apiService.getCurrentWeather(
                        latitude = location.latitude, longitude = location.longitude
                    ).body()
                )
            } catch (e: Exception) {
                Resource.Error(null, e.toString())
            }
        }

    override suspend fun getWeatherForecast(cityId: Int): Resource<List<WeatherForecastDto>> =
        withContext(ioDispatcher) {
            return@withContext try {
                Resource.Success(
                    data = apiService.getWeatherForecast(
                        cityId = cityId
                    ).body()?.weathers
                )
            } catch (e: Exception) {
                Resource.Error(null, e.toString())
            }
        }
}