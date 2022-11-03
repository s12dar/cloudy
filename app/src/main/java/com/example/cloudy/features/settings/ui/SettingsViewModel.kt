package com.example.cloudy.features.settings.ui

import androidx.lifecycle.*
import com.example.cloudy.features.settings.data.datastore.PreferencesManager
import com.example.cloudy.features.settings.data.datastore.ThemeSelection
import com.zhuinden.flowcombinetuplekt.combineTuple
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val preferenceManager: PreferencesManager,
    savedStateHandle: SavedStateHandle
) : ViewModel(), SettingsScreenActions {

    private val appPreferences = preferenceManager.appPreferences
    private val showThemeDialog =
        savedStateHandle.getLiveData("showThemeDialog", false)

    var uiState = combineTuple(
        appPreferences,
        showThemeDialog.asFlow()
    ).map { (appPreferences, showThemeDialog) ->
        SettingsScreenState(
            appPreferences = appPreferences,
            showThemeDialog = showThemeDialog
        )

    }.asLiveData()

    override fun onThemePreferenceClicked() {
        showThemeDialog.value = true
    }

    override fun onThemeUpdated(theme: ThemeSelection) {
        showThemeDialog.value = false
        viewModelScope.launch {
            preferenceManager.updateSelectedTheme(theme)
        }
    }

    override fun onThemeDialogDismissed() {
        showThemeDialog.value = false
    }
}