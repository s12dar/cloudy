package com.example.cloudy.features.home.di

import com.example.cloudy.features.home.data.local.WeatherLocalDataSource
import com.example.cloudy.features.home.data.local.WeatherLocalDataSourceImpl
import com.example.cloudy.features.home.data.remote.WeatherRemoteDataSource
import com.example.cloudy.features.home.data.remote.WeatherRemoteDataSourceImpl
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