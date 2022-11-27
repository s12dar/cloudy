package com.lyvetech.cloudy.features

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.lyvetech.cloudy.features.settings.data.datastore.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    preferencesManager: PreferencesManager
) : ViewModel() {
    val appPreferences = preferencesManager.appPreferences.asLiveData()
}