package com.lyvetech.cloudy.features.home.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lyvetech.cloudy.components.HomeBody
import com.lyvetech.cloudy.components.WeatherDataDisplay
import com.lyvetech.cloudy.core.ui.UiState
import com.lyvetech.cloudy.core.util.Constants
import com.lyvetech.cloudy.features.home.data.util.formatDate
import com.lyvetech.cloudy.features.home.data.util.manageLocation
import com.lyvetech.cloudy.features.settings.data.util.convertCelsiusToFahrenheit

@OptIn(ExperimentalMaterialApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    modifier: Modifier
) {

    val uiState by viewModel.getUiState()
    val isRefreshing = uiState is UiState.Loading

    val pullRefreshState =
        rememberPullRefreshState(refreshing = isRefreshing, { viewModel.getWeatherInfo() })

    val scrollState = rememberScrollState()

    when (uiState) {
        is UiState.Success -> {
            val viewState = (uiState as UiState.Success).data
            val selectedTempUnit = viewState.appPreferences.collectAsState(
                HomeScreenState.appPreferencesInitialState
            ).value.selectedTempUnit

            Box(
                modifier = modifier.pullRefresh(pullRefreshState)
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
                                location = manageLocation(weatherInfo.location),
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

                        PullRefreshIndicator(
                            refreshing = isRefreshing,
                            state = pullRefreshState,
                            modifier = Modifier.align(Alignment.TopCenter)
                        )
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