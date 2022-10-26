package com.example.cloudy.features.home.ui

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cloudy.core.ui.UiState
import com.example.cloudy.features.home.data.repository.WeatherRepository
import com.example.cloudy.features.home.domain.service.LocationTracker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val locationTracker: LocationTracker
) : ViewModel() {

    private val uiState = mutableStateOf<UiState<HomeScreenState>>(UiState.Loading)
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
                                location.latitude,
                                location.longitude
                            ).data
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