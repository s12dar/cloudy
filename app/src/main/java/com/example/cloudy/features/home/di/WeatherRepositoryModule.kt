package com.example.cloudy.features.home.di

import com.example.cloudy.features.home.data.repository.WeatherRepository
import com.example.cloudy.features.home.data.repository.WeatherRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class WeatherRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepository(repository: WeatherRepositoryImpl): WeatherRepository
}