package com.lyvetech.cloudy.features.home.ui

import android.os.Build
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
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
import com.lyvetech.cloudy.R
import com.lyvetech.cloudy.components.HomeBody
import com.lyvetech.cloudy.components.WeatherDataDisplay
import com.lyvetech.cloudy.core.theme.CloudyTheme
import com.lyvetech.cloudy.core.ui.UiState
import com.lyvetech.cloudy.core.util.Constants
import com.lyvetech.cloudy.features.home.data.util.manageLocation
import com.lyvetech.cloudy.features.settings.data.util.convertCelsiusToFahrenheit

@RequiresApi(Build.VERSION_CODES.O)
@Composable
internal fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val homeUiState by viewModel.getUiState()

    HomeScreen(
        modifier = modifier,
        uiState = homeUiState,
        getWeatherInfo = viewModel::getWeatherInfo
    )
}

@OptIn(ExperimentalMaterialApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
internal fun HomeScreen(
    modifier: Modifier,
    uiState: UiState<HomeScreenState>,
    getWeatherInfo: () -> Unit
) {
    val isFeedLoading = uiState is UiState.Loading
    val pullRefreshState =
        rememberPullRefreshState(refreshing = isFeedLoading, { getWeatherInfo() })

    val scrollState = rememberScrollState()

    when (uiState) {
        is UiState.Success -> {
            val viewState = uiState.data
            val selectedTempUnit = viewState.appPreferences.collectAsState(
                HomeScreenState.appPreferencesInitialState
            ).value.selectedTempUnit

            viewState.weatherInfo?.let { weatherInfo ->
                weatherInfo.currentWeatherData?.let {
                    HomeContent(
                        modifier = modifier,
                        pullRefreshState = pullRefreshState,
                        scrollState = scrollState,
                        isFeedLoading = isFeedLoading,
                        location = manageLocation(weatherInfo.location),
                        temperature = if (selectedTempUnit.toString() == Constants.FAHRENHEIT) "${
                            convertCelsiusToFahrenheit(it.temperatureCelsius)
                        }${Constants.FAHRENHEIT_SIGN}" else "${it.temperatureCelsius}${Constants.CELSIUS_SIGN}",
                        weatherType = it.weatherType.weatherDesc,
                        humidity = it.humidity,
                        windSpeed = it.windSpeed,
                        pressure = it.pressure,
                        img = it.weatherType.iconRes
                    )
                }
            }
        }

        is UiState.Error -> {}

        is UiState.Loading -> {}

    }

    LaunchedEffect(Unit) {
        getWeatherInfo()
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun HomeContent(
    modifier: Modifier,
    pullRefreshState: PullRefreshState,
    scrollState: ScrollState,
    isFeedLoading: Boolean,
    location: String,
    temperature: String,
    weatherType: String,
    humidity: Double,
    pressure: Double,
    windSpeed: Double,
    @DrawableRes img: Int
) {
    Box(
        modifier = modifier.pullRefresh(pullRefreshState)
    ) {
        Column(
            modifier = Modifier.verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HomeBody(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 8.dp, end = 8.dp),
                location = location,
                lastFetchTime = "",
                img = img,
                temperature = temperature,
                weatherType = weatherType
            )
            Spacer(modifier = Modifier.height(56.dp))

            WeatherDataDisplay(
                modifier = Modifier.paddingFromBaseline(top = 56.dp),
                humidity = humidity,
                pressure = pressure,
                windSpeed = windSpeed
            )
        }

        PullRefreshIndicator(
            refreshing = isFeedLoading,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    CloudyTheme {
        HomeContent(
            modifier = Modifier.fillMaxSize(),
            pullRefreshState = rememberPullRefreshState(refreshing = false, {}),
            scrollState = rememberScrollState(),
            isFeedLoading = false,
            location = "Kreuzberg, Berlin",
            temperature = "12°C",
            weatherType = "Clear Sky",
            img = R.drawable.ic_clear_sky,
            humidity = 12.0,
            windSpeed = 12.0,
            pressure = 12.0
        )
    }
}