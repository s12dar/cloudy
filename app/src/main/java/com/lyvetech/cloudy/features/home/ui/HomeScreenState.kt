package com.lyvetech.cloudy.features.home.ui

import com.lyvetech.cloudy.features.home.domain.model.WeatherInfo
import com.lyvetech.cloudy.features.settings.data.datastore.AppPreferences
import com.lyvetech.cloudy.features.settings.data.datastore.TempUnitSelection
import com.lyvetech.cloudy.features.settings.data.datastore.ThemeSelection
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