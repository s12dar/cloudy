package com.example.cloudy.features.home.di

import com.example.cloudy.features.home.data.local.HomeLocalDataSource
import com.example.cloudy.features.home.data.local.HomeLocalDataSourceImpl
import com.example.cloudy.features.home.data.remote.HomeRemoteDataSource
import com.example.cloudy.features.home.data.remote.HomeRemoteDataSourceImpl
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