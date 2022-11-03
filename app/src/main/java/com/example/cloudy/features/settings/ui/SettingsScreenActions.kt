package com.example.cloudy.features.settings.ui

import com.example.cloudy.features.settings.data.datastore.ThemeSelection

interface SettingsScreenActions {
    fun onThemePreferenceClicked()
    fun onThemeUpdated(theme: ThemeSelection)
    fun onThemeDialogDismissed()
}