package com.example.cloudy.features

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.cloudy.features.settings.data.datastore.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val preferencesManager: PreferencesManager
) : ViewModel() {
    val appPreferences = preferencesManager.appPreferences.asLiveData()
}