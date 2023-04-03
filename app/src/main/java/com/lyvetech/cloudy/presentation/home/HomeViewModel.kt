package com.lyvetech.cloudy.presentation.home

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lyvetech.cloudy.common.ui.UiState
import com.lyvetech.cloudy.domain.repository.HomeRepository
import com.lyvetech.cloudy.domain.service.LocationTracker
import com.lyvetech.cloudy.domain.pref.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository,
    private val locationTracker: LocationTracker,
    private val preferencesManager: PreferencesManager
) : ViewModel() {

    private val uiState = mutableStateOf<UiState<HomeScreenState>>(UiState.Loading)
    private val appPreferences = preferencesManager.appPreferences
    fun getUiState(): State<UiState<HomeScreenState>> = uiState

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SuspiciousIndentation")
    fun getWeatherInfo() {
        viewModelScope.launch {
            uiState.value = UiState.Loading

            locationTracker.getCurrentLocation().data?.let { location ->
                try {
                    uiState.value = UiState.Success(
                        HomeScreenState(
                            weatherInfo = repository.getWeatherInfo(
                                location
                            ).data,
                            appPreferences = appPreferences
                        )
                    )
                } catch (e: Exception) {
                    uiState.value = UiState.Error(Exception(e))
                }
            } ?: kotlin.run {
                uiState.value =
                    UiState.Error(Exception("Couldn't retrieve location. Make sure you grant location permissions"))
            }
        }
    }
}