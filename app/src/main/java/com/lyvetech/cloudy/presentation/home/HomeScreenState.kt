package com.lyvetech.cloudy.presentation.home

import com.lyvetech.cloudy.common.model.LocationModel
import com.lyvetech.cloudy.data.pref.AppPreferences
import com.lyvetech.cloudy.data.pref.TempUnitSelection
import com.lyvetech.cloudy.data.pref.ThemeSelection
import com.lyvetech.cloudy.domain.model.WeatherInfo

data class HomeScreenState(
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