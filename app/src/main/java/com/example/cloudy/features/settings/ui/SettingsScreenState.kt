package com.example.cloudy.features.settings.ui

import com.example.cloudy.BuildConfig
import com.example.cloudy.features.settings.data.datastore.AppPreferences
import com.example.cloudy.features.settings.data.datastore.TempUnitSelection
import com.example.cloudy.features.settings.data.datastore.ThemeSelection

data class SettingsScreenState(
    val appPreferences: AppPreferences,
    val showThemeDialog: Boolean,
    val showTempUnitDialog: Boolean,
    val appVersionName: String = BuildConfig.VERSION_NAME
) {
    companion object {
        val initialState = SettingsScreenState(
            appPreferences = AppPreferences(
                selectedTheme = ThemeSelection.SYSTEM,
                selectedTempUnit = TempUnitSelection.CELSIUS
            ),
            showThemeDialog = false,
            showTempUnitDialog = false
        )
    }
}