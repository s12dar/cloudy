package com.lyvetech.cloudy.di

import com.lyvetech.cloudy.data.local.WeatherLocalDataSource
import com.lyvetech.cloudy.data.local.WeatherLocalDataSourceImpl
import com.lyvetech.cloudy.data.remote.WeatherRemoteDataSource
import com.lyvetech.cloudy.data.remote.WeatherRemoteDataSourceImpl
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