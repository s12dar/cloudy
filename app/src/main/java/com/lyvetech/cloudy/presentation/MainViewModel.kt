package com.lyvetech.cloudy.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.lyvetech.cloudy.domain.pref.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    preferencesManager: PreferencesManager
) : ViewModel() {
    val appPreferences = preferencesManager.appPreferences.asLiveData()
}