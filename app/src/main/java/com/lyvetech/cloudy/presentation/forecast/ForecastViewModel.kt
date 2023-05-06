package com.lyvetech.cloudy.presentation.forecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lyvetech.cloudy.domain.pref.PreferencesManager
import com.lyvetech.cloudy.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val preferencesManager: PreferencesManager,
) : ViewModel() {

    private var _uiState = MutableStateFlow(ForecastScreenState())
    val uiState: StateFlow<ForecastScreenState> = _uiState.asStateFlow()

    init {
        getWeatherForecast()
    }

    internal fun getWeatherForecast() {
        _uiState.value = _uiState.value.copy(isLoading = true)
        viewModelScope.launch {
            preferencesManager.appPreferences.collect { appPreferences ->
                repository.getWeatherForecast(appPreferences.savedCityId)
                    .collect { weatherForecastList ->
                        _uiState.value = _uiState.value.copy(
                            weatherForecastList = weatherForecastList.data,
                            appPreferences = appPreferences,
                            isLoading = false
                        )
                    }
            }
        }
    }

    fun refreshWeatherForecast() {
        getWeatherForecast()
    }
}