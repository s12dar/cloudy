package com.lyvetech.cloudy.features.settings.data.datastore

import android.content.Context
import androidx.annotation.StringRes
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.lyvetech.cloudy.R
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import logcat.asLog
import logcat.logcat
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

enum class ThemeSelection(@StringRes val readableName: Int) {
    SYSTEM(R.string.system_default), LIGHT(R.string.light), DARK(R.string.dark)
}

enum class TempUnitSelection(@StringRes val readableName: Int) {
    FAHRENHEIT(R.string.unit_fahrenheit), CELSIUS(R.string.unit_celsius)
}

data class AppPreferences(
    val selectedTheme: ThemeSelection,
    val selectedTempUnit: TempUnitSelection
)

@Singleton
class DefaultPreferencesManager @Inject constructor(
    @ApplicationContext private val context: Context
) : PreferencesManager {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "preferences")

    override val appPreferences: Flow<AppPreferences> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                logcat { exception.asLog() }
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            val selectedTheme = ThemeSelection.valueOf(
                preferences[PreferencesKeys.SELECTED_THEME] ?: ThemeSelection.SYSTEM.name
            )
            val selectedTempUnit = TempUnitSelection.valueOf(
                preferences[PreferencesKeys.SELECTED_TEMP_UNIT] ?: TempUnitSelection.CELSIUS.name
            )
            AppPreferences(
                selectedTheme = selectedTheme,
                selectedTempUnit = selectedTempUnit
            )
        }

    override suspend fun updateSelectedTheme(theme: ThemeSelection) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.SELECTED_THEME] = theme.name
        }
    }

    override suspend fun updatedSelectedTempUnit(tempUnit: TempUnitSelection) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.SELECTED_TEMP_UNIT] = tempUnit.name
        }
    }

    override suspend fun getTempUnitSelection(): Flow<TempUnitSelection> {
        return context.dataStore.data.map { preferences ->
            TempUnitSelection.valueOf(
                preferences[PreferencesKeys.SELECTED_TEMP_UNIT] ?: TempUnitSelection.CELSIUS.name
            )
        }
    }

    private object PreferencesKeys {
        val SELECTED_THEME = stringPreferencesKey("SELECTED_THEME")
        val SELECTED_TEMP_UNIT = stringPreferencesKey("SELECTED_TEMP_UNIT")
    }
}