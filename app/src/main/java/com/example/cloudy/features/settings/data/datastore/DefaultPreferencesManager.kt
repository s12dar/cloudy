package com.example.cloudy.features.settings.data.datastore

import android.content.Context
import androidx.annotation.StringRes
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.cloudy.R
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

data class AppPreferences(
    val selectedTheme: ThemeSelection
)

@Singleton
class DefaultPreferencesManager @Inject constructor(
    @ApplicationContext private val context: Context,
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
            AppPreferences(
                selectedTheme = selectedTheme,
            )
        }

    override suspend fun updateSelectedTheme(theme: ThemeSelection) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.SELECTED_THEME] = theme.name
        }
    }

    private object PreferencesKeys {
        val SELECTED_THEME = stringPreferencesKey("SELECTED_THEME")
    }
}