package com.lyvetech.cloudy.presentation.settings

import com.lyvetech.cloudy.data.pref.AppPreferences
import com.lyvetech.cloudy.data.pref.TempUnitSelection
import com.lyvetech.cloudy.data.pref.ThemeSelection

data class SettingsScreenState(
    val appPreferences: AppPreferences,
    val showThemeDialog: Boolean,
    val showTempUnitDialog: Boolean,
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