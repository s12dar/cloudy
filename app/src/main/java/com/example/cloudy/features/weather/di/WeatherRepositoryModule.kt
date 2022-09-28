package com.example.cloudy.features.weather.di

import com.example.cloudy.features.weather.data.repository.WeatherRepository
import com.example.cloudy.features.weather.data.repository.WeatherRepositoryImpl
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