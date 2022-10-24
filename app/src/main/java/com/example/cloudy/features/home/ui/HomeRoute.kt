package com.example.cloudy.features.home.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cloudy.R
import com.example.cloudy.components.HomeBody
import com.example.cloudy.components.WeatherDataDisplay
import com.example.cloudy.features.home.data.util.formatDate
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.lyvetech.cloudy.core.component.WeatherTopAppBar

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel()
) {

    val uiState = viewModel.state

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = false)

    viewModel.loadWeatherInfo()
    HomeScreen(
        swipeRefreshState = swipeRefreshState, uiState = uiState, viewModel = viewModel
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    swipeRefreshState: SwipeRefreshState,
    uiState: HomeScreenState,
    viewModel: HomeViewModel,

    ) {

    Scaffold(
        topBar = {
            WeatherTopAppBar(
                titleRes = R.string.app_name,
                modifier = modifier
            )
        }, modifier = Modifier.fillMaxSize()
    ) { paddingValues ->

        SwipeRefresh(
            swipeRefreshState, onRefresh = {
                viewModel.loadWeatherInfo()
            }, indicatorPadding = paddingValues
        ) {
            uiState.weatherInfo?.let { weatherInfo ->
                weatherInfo.currentWeatherData?.let {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        HomeBody(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp, start = 8.dp, end = 8.dp),
                            location = uiState.weatherInfo.location,
                            lastFetchTime = weatherInfo.formatDate(),
                            img = it.weatherType.iconRes,
                            temperature = it.temperatureCelsius.toString(),
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
}