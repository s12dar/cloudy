package com.lyvetech.cloudy.features.settings.ui

import com.lyvetech.cloudy.BuildConfig
import com.lyvetech.cloudy.features.settings.data.datastore.AppPreferences
import com.lyvetech.cloudy.features.settings.data.datastore.TempUnitSelection
import com.lyvetech.cloudy.features.settings.data.datastore.ThemeSelection

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