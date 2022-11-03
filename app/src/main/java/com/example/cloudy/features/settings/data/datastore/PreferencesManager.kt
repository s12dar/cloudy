package com.example.cloudy.features.settings.data.datastore

import kotlinx.coroutines.flow.Flow


interface PreferencesManager {
    val appPreferences: Flow<AppPreferences>

    suspend fun updateSelectedTheme(theme: ThemeSelection)
}