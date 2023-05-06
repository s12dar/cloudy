package com.lyvetech.cloudy.domain.pref

import com.lyvetech.cloudy.data.pref.AppPreferences
import com.lyvetech.cloudy.data.pref.TempUnitSelection
import com.lyvetech.cloudy.data.pref.ThemeSelection
import kotlinx.coroutines.flow.Flow


interface PreferencesManager {
    val appPreferences: Flow<AppPreferences>
    suspend fun updateSelectedTheme(theme: ThemeSelection)
    suspend fun updatedSelectedTempUnit(tempUnit: TempUnitSelection)
    suspend fun updateCityId(cityId: Int)
}