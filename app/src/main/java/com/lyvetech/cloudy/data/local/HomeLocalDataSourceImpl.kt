package com.lyvetech.cloudy.data.local

import com.lyvetech.cloudy.data.local.dao.WeatherDao
import com.lyvetech.cloudy.data.local.entity.WeatherEntity
import com.lyvetech.cloudy.data.local.entity.WeatherForecastEntity
import com.lyvetech.cloudy.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeLocalDataSourceImpl @Inject constructor(
    private val weatherDao: WeatherDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : HomeLocalDataSource {
    override suspend fun insertWeather(weatherEntity: WeatherEntity) =
        withContext(ioDispatcher) {
            weatherDao.insertWeather(weatherEntity = weatherEntity)
        }

    override suspend fun insertWeatherForecast(weatherForecastEntity: WeatherForecastEntity) =
        withContext(ioDispatcher) {
            weatherDao.insertWeatherForecast(weatherForecastEntity = weatherForecastEntity)
        }

    override suspend fun getWeather(): WeatherEntity? =
        withContext(ioDispatcher) {
            weatherDao.getWeather()
        }

    override suspend fun getWeatherForecast(): WeatherForecastEntity? {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllWeather() {
        withContext(ioDispatcher) {
            weatherDao.deleteAllWeather()
        }
    }

    override suspend fun deleteAllWeatherForecast() {
        TODO("Not yet implemented")
    }
}