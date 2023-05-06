package com.lyvetech.cloudy.data.local

import com.lyvetech.cloudy.data.local.dao.WeatherDao
import com.lyvetech.cloudy.data.local.entity.WeatherEntity
import com.lyvetech.cloudy.data.local.entity.WeatherForecastEntity
import com.lyvetech.cloudy.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherLocalDataSourceImpl @Inject constructor(
    private val weatherDao: WeatherDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : WeatherLocalDataSource {
    override suspend fun insertWeather(weatherEntity: WeatherEntity) =
        withContext(ioDispatcher) {
            weatherDao.insertWeather(weatherEntity = weatherEntity)
        }

    override suspend fun insertWeatherForecast(weatherForecastEntity: List<WeatherForecastEntity>) =
        withContext(ioDispatcher) {
            weatherDao.insertWeatherForecast(weatherForecastEntity = weatherForecastEntity)
        }

    override suspend fun getWeather(): WeatherEntity? =
        withContext(ioDispatcher) {
            weatherDao.getWeather()
        }

    override suspend fun getWeatherForecast(): List<WeatherForecastEntity>? =
        withContext(ioDispatcher) {
            weatherDao.getWeatherForecast()
        }

    override suspend fun deleteAllWeather() {
        withContext(ioDispatcher) {
            weatherDao.deleteAllWeather()
        }
    }

    override suspend fun deleteAllWeatherForecast() {
        withContext(ioDispatcher) {
            weatherDao.deleteAllWeatherForecast()
        }
    }
}