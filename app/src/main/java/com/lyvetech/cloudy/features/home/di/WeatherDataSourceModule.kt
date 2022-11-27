package com.lyvetech.cloudy.features.home.di

import com.lyvetech.cloudy.features.home.data.local.HomeLocalDataSource
import com.lyvetech.cloudy.features.home.data.local.HomeLocalDataSourceImpl
import com.lyvetech.cloudy.features.home.data.remote.HomeRemoteDataSource
import com.lyvetech.cloudy.features.home.data.remote.HomeRemoteDataSourceImpl
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