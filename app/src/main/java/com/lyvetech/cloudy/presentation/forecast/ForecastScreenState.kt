package com.lyvetech.cloudy.presentation.forecast

import com.lyvetech.cloudy.data.pref.AppPreferences
import com.lyvetech.cloudy.data.pref.TempUnitSelection
import com.lyvetech.cloudy.data.pref.ThemeSelection
import com.lyvetech.cloudy.domain.model.WeatherInfo

data class ForecastScreenState(
    val weatherInfo: WeatherInfo? = null,
    val appPreferences: AppPreferences = appPreferencesInitialState,
    val isLoading: Boolean = false
) {
    companion object {
        val appPreferencesInitialState = AppPreferences(
            selectedTheme = ThemeSelection.SYSTEM,
            selectedTempUnit = TempUnitSelection.CELSIUS
        )
    }
}