package com.lyvetech.cloudy.di

import com.lyvetech.cloudy.data.remote.retrofit.WeatherApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object WeatherNetworkModule {

    @Provides
    @Singleton
    fun provideWeatherApiService(
        retrofit: Retrofit
    ): WeatherApiService = retrofit.create(WeatherApiService::class.java)
}