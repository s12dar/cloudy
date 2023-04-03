package com.lyvetech.cloudy.di

import com.lyvetech.cloudy.data.local.HomeLocalDataSource
import com.lyvetech.cloudy.data.local.HomeLocalDataSourceImpl
import com.lyvetech.cloudy.data.remote.HomeRemoteDataSource
import com.lyvetech.cloudy.data.remote.HomeRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class WeatherDataSourceModule {

    @Binds
    abstract fun bindLocalDataSource(localDataSourceImpl: HomeLocalDataSourceImpl): HomeLocalDataSource

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSource: HomeRemoteDataSourceImpl): HomeRemoteDataSource
}