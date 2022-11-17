package com.example.cloudy.features.home.ui

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cloudy.core.ui.UiState
import com.example.cloudy.features.home.data.repository.HomeRepository
import com.example.cloudy.features.home.domain.service.LocationTracker
import com.example.cloudy.features.settings.data.datastore.PreferencesManager
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
                    uiState.value = UiState.Error(e.toString())
                }
            } ?: kotlin.run {
                uiState.value =
                    UiState.Error("Couldn't retrieve location. Make sure you grant location permissions")
            }
        }
    }
}