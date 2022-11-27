package com.lyvetech.cloudy.features.home.di

import android.app.Application
import com.lyvetech.cloudy.features.home.domain.service.LocationTracker
import com.lyvetech.cloudy.features.home.domain.service.LocationTrackerImpl
import com.google.android.gms.location.LocationServices
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class WeatherLocationServiceModule {

    @Binds
    abstract fun bindLocationTrackerService(locationTracker: LocationTrackerImpl): LocationTracker

    companion object {
        @Provides
        @Singleton
        fun provideFusedLocationProviderClient(
            app: Application
        ) = LocationServices.getFusedLocationProviderClient(app)
    }
}