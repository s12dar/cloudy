package com.example.cloudy.features.weather.di

import com.example.cloudy.features.weather.data.local.WeatherLocalDataSource
import com.example.cloudy.features.weather.data.local.WeatherLocalDataSourceImpl
import com.example.cloudy.features.weather.data.remote.WeatherRemoteDataSource
import com.example.cloudy.features.weather.data.remote.WeatherRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class WeatherDataSourceModule {

    @Binds
    abstract fun bindLocalDataSource(localDataSourceImpl: WeatherLocalDataSourceImpl): WeatherLocalDataSource

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSource: WeatherRemoteDataSourceImpl): WeatherRemoteDataSource
}