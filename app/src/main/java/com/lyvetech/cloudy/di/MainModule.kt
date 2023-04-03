package com.lyvetech.cloudy.di

import com.lyvetech.cloudy.data.pref.DefaultPreferencesManager
import com.lyvetech.cloudy.domain.pref.PreferencesManager
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