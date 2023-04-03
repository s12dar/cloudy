package com.lyvetech.cloudy.presentation.home

import com.lyvetech.cloudy.domain.model.WeatherInfo
import com.lyvetech.cloudy.data.pref.AppPreferences
import com.lyvetech.cloudy.data.pref.TempUnitSelection
import com.lyvetech.cloudy.data.pref.ThemeSelection
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