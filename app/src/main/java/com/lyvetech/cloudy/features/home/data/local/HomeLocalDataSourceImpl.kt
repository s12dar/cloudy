package com.lyvetech.cloudy.features.home.data.local

import com.lyvetech.cloudy.core.di.IoDispatcher
import com.lyvetech.cloudy.features.home.data.local.dao.WeatherDao
import com.lyvetech.cloudy.features.home.data.local.entity.WeatherEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeLocalDataSourceImpl @Inject constructor(
    private val weatherDao: WeatherDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : HomeLocalDataSource {
    override suspend fun insertWeather(weather: WeatherEntity) =
        withContext(ioDispatcher) {
            weatherDao.insertWeather(weatherData = weather)
        }

    override suspend fun getWeather(): WeatherEntity? =
        withContext(ioDispatcher) {
            weatherDao.getWeatherInfo()
        }

    override suspend fun deleteAllWeather() {
        withContext(ioDispatcher) {
            weatherDao.deleteAllWeather()
        }
    }
}