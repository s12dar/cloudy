package com.example.cloudy.features.weather.di

import com.example.cloudy.core.data.local.WeatherDatabase
import com.example.cloudy.features.weather.data.local.dao.WeatherDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object WeatherDatabaseModule {

    @Singleton
    @Provides
    fun provideWeatherDao(
        weatherDb: WeatherDatabase
    ): WeatherDao = weatherDb.weatherDao
}