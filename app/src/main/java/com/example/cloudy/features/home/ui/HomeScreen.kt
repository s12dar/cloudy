package com.example.cloudy.features.home.ui

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cloudy.components.HomeBody
import com.example.cloudy.components.WeatherDataDisplay
import com.example.cloudy.core.ui.UiState
import com.example.cloudy.core.util.Constants
import com.example.cloudy.features.home.data.util.formatDate
import com.example.cloudy.features.settings.data.util.convertCelsiusToFahrenheit
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    modifier: Modifier
) {

    val uiState by viewModel.getUiState()

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = uiState is UiState.Loading)

    val scrollState = rememberScrollState()

    when (uiState) {
        is UiState.Success -> {
            val viewState = (uiState as UiState.Success).data
            val selectedTempUnit = viewState.appPreferences.collectAsState(
                HomeScreenState.appPreferencesInitialState
            ).value.selectedTempUnit

            SwipeRefresh(
                state = swipeRefreshState,
                onRefresh = {
                    viewModel.getWeatherInfo()
                },
                modifier = modifier
            ) {
                viewState.weatherInfo?.let { weatherInfo ->
                    weatherInfo.currentWeatherData?.let {
                        Column(
                            modifier = Modifier.verticalScroll(scrollState),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            HomeBody(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 8.dp, start = 8.dp, end = 8.dp),
                                location = weatherInfo.location,
                                lastFetchTime = weatherInfo.formatDate(),
                                img = it.weatherType.iconRes,
                                temperature = if (selectedTempUnit.toString() == Constants.FAHRENHEIT) "${
                                    convertCelsiusToFahrenheit(it.temperatureCelsius)
                                }${Constants.FAHRENHEIT_SIGN}" else "${it.temperatureCelsius}${Constants.CELSIUS_SIGN}",
                                weatherType = it.weatherType.weatherDesc
                            )
                            Spacer(modifier = Modifier.height(56.dp))

                            WeatherDataDisplay(
                                modifier = Modifier.paddingFromBaseline(top = 56.dp),
                                weatherData = it
                            )
                        }
                    }
                }

            }
        }

        is UiState.Error -> {}

        is UiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.getWeatherInfo()
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(modifier = Modifier.fillMaxSize())
}