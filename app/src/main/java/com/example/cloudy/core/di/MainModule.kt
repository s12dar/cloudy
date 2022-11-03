package com.example.cloudy.core.di

import com.example.cloudy.features.settings.data.datastore.DefaultPreferencesManager
import com.example.cloudy.features.settings.data.datastore.PreferencesManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class MainModule {

    @Binds
    @Singleton
    abstract fun bindPreferenceManager(preferenceManager: DefaultPreferencesManager): PreferencesManager
}