package com.lyvetech.cloudy.di

import android.app.Application
import androidx.room.Room
import com.lyvetech.cloudy.data.local.db.WeatherDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {

    @Provides
    @Singleton
    fun provideWeatherDb(app: Application): WeatherDatabase {
        return Room.databaseBuilder(
            app,
            WeatherDatabase::class.java, WeatherDatabase.DB_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }
}