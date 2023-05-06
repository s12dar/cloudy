package com.lyvetech.cloudy.presentation.home

import android.Manifest
import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.lyvetech.cloudy.R
import com.lyvetech.cloudy.common.Constants
import com.lyvetech.cloudy.common.ui.theme.CloudyTheme
import com.lyvetech.cloudy.common.utils.toCelsius
import com.lyvetech.cloudy.common.utils.toDateFormat
import com.lyvetech.cloudy.common.utils.toFahrenheit
import com.lyvetech.cloudy.domain.model.WeatherType
import com.lyvetech.cloudy.presentation.home.components.HomeBody
import com.lyvetech.cloudy.presentation.home.components.WeatherDataDisplay

@SuppressLint("PermissionLaunchedDuringComposition")
@OptIn(ExperimentalPermissionsApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
internal fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val homeUiState by viewModel.uiState.collectAsState()
    val permissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)

    LaunchedEffect(permissionState.hasPermission) {
        if (permissionState.hasPermission) {
            viewModel.refreshWeather()
        }
    }

    if (permissionState.hasPermission) {
        HomeScreen(
            modifier = modifier,
            uiState = homeUiState,
            refreshWeather = viewModel::refreshWeather
        )
    } else {
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_no_weather_info),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                )

                Text(
                    text = stringResource(id = R.string.location_permission_msg),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
internal fun HomeScreen(
    modifier: Modifier,
    uiState: HomeScreenState,
    refreshWeather: () -> Unit
) {
    val isFeedLoading = uiState.isLoading
    val pullRefreshState =
        rememberPullRefreshState(refreshing = isFeedLoading, { refreshWeather() })
    val scrollState = rememberScrollState()

    val selectedTempUnit = uiState.appPreferences.selectedTempUnit

    uiState.weather?.let {
        HomeContent(
            modifier = modifier,
            pullRefreshState = pullRefreshState,
            scrollState = scrollState,
            isFeedLoading = isFeedLoading,
            location = it.name,
            lastFetchedTime = it.lastFetchedTime.toDateFormat(),
            temperature = if (selectedTempUnit.toString() == Constants.FAHRENHEIT) "${
                (it.networkWeatherCondition.temp.toFahrenheit())
            }${Constants.FAHRENHEIT_SIGN}" else "${it.networkWeatherCondition.temp.toCelsius()}${Constants.CELSIUS_SIGN}",
            weatherType = it.networkWeatherDescription.first().description.toString(),
            humidity = it.networkWeatherCondition.humidity,
            windSpeed = it.wind.speed,
            pressure = it.networkWeatherCondition.pressure,
            img = WeatherType.fromWMO(it.networkWeatherDescription.first().icon.toString()).iconRes
        )
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
    lastFetchedTime: String,
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
                lastFetchTime = lastFetchedTime,
                img = img,
                temperature = temperature,
                weatherType = weatherType
            )
            Spacer(modifier = Modifier.height(96.dp))

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
            lastFetchedTime = "",
            humidity = 12.0,
            windSpeed = 12.0,
            pressure = 12.0
        )
    }
}