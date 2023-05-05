package com.lyvetech.cloudy.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lyvetech.cloudy.domain.pref.PreferencesManager
import com.lyvetech.cloudy.domain.repository.HomeRepository
import com.lyvetech.cloudy.domain.service.LocationTracker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository,
    private val locationTracker: LocationTracker,
    private val preferencesManager: PreferencesManager
) : ViewModel() {

    private var _uiState = MutableStateFlow(HomeScreenState())
    val uiState: StateFlow<HomeScreenState> = _uiState.asStateFlow()

    init {
        getWeatherInfo()
    }

    internal fun getWeatherInfo() {
        _uiState.value = _uiState.value.copy(isLoading = true)
        viewModelScope.launch {
            locationTracker.getCurrentLocation().data?.let { location ->
                repository.getWeather(location).collect { weatherInfoResponse ->
                    preferencesManager.appPreferences.collect { appPreferences ->
                        _uiState.value = _uiState.value.copy(
                            weather = weatherInfoResponse.data,
                            appPreferences = appPreferences
                        )
                    }
                }
            }
        }

        _uiState.value = _uiState.value.copy(isLoading = false)
    }

    fun refreshWeather() {
        getWeatherInfo()
    }
}