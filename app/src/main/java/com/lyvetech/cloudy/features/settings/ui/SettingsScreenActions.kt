package com.lyvetech.cloudy.features.settings.ui

import com.lyvetech.cloudy.features.settings.data.datastore.TempUnitSelection
import com.lyvetech.cloudy.features.settings.data.datastore.ThemeSelection

interface SettingsScreenActions {
    fun onThemePreferenceClicked()
    fun onTempUnitPreferenceClicked()
    fun onThemeUpdated(theme: ThemeSelection)
    fun onTempUnitUpdated(tempUnit: TempUnitSelection)
    fun onThemeDialogDismissed()
    fun onTempUnitDialogDismissed()
}