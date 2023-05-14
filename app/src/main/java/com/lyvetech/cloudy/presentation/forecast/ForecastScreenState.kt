package com.lyvetech.cloudy.presentation.forecast

import com.lyvetech.cloudy.data.pref.AppPreferences
import com.lyvetech.cloudy.data.pref.TempUnitSelection
import com.lyvetech.cloudy.data.pref.ThemeSelection
import com.lyvetech.cloudy.domain.model.WeatherForecast

data class ForecastScreenState(
    val weatherForecastList: List<WeatherForecast>? = null,
    val filteredForecast: List<WeatherForecast>? = null,
    val appPreferences: AppPreferences = appPreferencesInitialState,
    val isLoading: Boolean = false
) {
    companion object {
        val appPreferencesInitialState = AppPreferences(
            selectedTheme = ThemeSelection.SYSTEM,
            selectedTempUnit = TempUnitSelection.CELSIUS,
            savedCityId = -1
        )
    }
}