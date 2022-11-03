package com.example.cloudy.features.settings.ui

import com.example.cloudy.features.settings.data.datastore.AppPreferences
import com.example.cloudy.features.settings.data.datastore.ThemeSelection

data class SettingsScreenState(
    val appPreferences: AppPreferences,
    val showThemeDialog: Boolean
) {
    companion object {
        val initialState = SettingsScreenState(
            appPreferences = AppPreferences(
                selectedTheme = ThemeSelection.SYSTEM
            ),
            showThemeDialog = false,
        )
    }
}