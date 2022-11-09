package com.example.cloudy.features.home.ui

import com.example.cloudy.features.home.domain.model.WeatherInfo
import com.example.cloudy.features.settings.data.datastore.AppPreferences
import com.example.cloudy.features.settings.data.datastore.TempUnitSelection
import com.example.cloudy.features.settings.data.datastore.ThemeSelection
import kotlinx.coroutines.flow.Flow

class HomeScreenState(
    val weatherInfo: WeatherInfo?,
    val appPreferences: Flow<AppPreferences>
) {
    companion object {
        val appPreferencesInitialState = AppPreferences(
            selectedTheme = ThemeSelection.SYSTEM,
            selectedTempUnit = TempUnitSelection.CELSIUS
        )
    }
}