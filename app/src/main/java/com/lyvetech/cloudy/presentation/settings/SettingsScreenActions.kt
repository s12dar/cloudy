package com.lyvetech.cloudy.presentation.settings

import com.lyvetech.cloudy.data.pref.TempUnitSelection
import com.lyvetech.cloudy.data.pref.ThemeSelection

interface SettingsScreenActions {
    fun onThemePreferenceClicked()
    fun onTempUnitPreferenceClicked()
    fun onThemeUpdated(theme: ThemeSelection)
    fun onTempUnitUpdated(tempUnit: TempUnitSelection)
    fun onThemeDialogDismissed()
    fun onTempUnitDialogDismissed()
}