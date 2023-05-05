package com.lyvetech.cloudy.presentation.home

import com.lyvetech.cloudy.data.pref.AppPreferences
import com.lyvetech.cloudy.data.pref.TempUnitSelection
import com.lyvetech.cloudy.data.pref.ThemeSelection
import com.lyvetech.cloudy.domain.model.Weather

data class HomeScreenState(
    val weather: Weather? = null,
    val appPreferences: AppPreferences = appPreferencesInitialState,
    val isLoading: Boolean = false,
    val errorMsg: String? = null
) {
    companion object {
        val appPreferencesInitialState = AppPreferences(
            selectedTheme = ThemeSelection.SYSTEM,
            selectedTempUnit = TempUnitSelection.CELSIUS
        )
    }
}