package com.lyvetech.cloudy.di

import com.lyvetech.cloudy.data.local.db.WeatherDatabase
import com.lyvetech.cloudy.data.local.dao.WeatherDao
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