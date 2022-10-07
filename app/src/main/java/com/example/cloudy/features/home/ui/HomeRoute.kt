package com.example.cloudy.features.home.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cloudy.R
import com.example.cloudy.components.HomeHeader
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.lyvetech.cloudy.core.component.WeatherTopAppBar

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val uiState = viewModel.state

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = uiState.isLoading)

    val scrollState = rememberScrollState()

    viewModel.loadWeatherInfo()
    HomeScreen(
        swipeRefreshState = swipeRefreshState,
        uiState = uiState,
        viewModel = viewModel
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    swipeRefreshState: SwipeRefreshState,
    uiState: HomeScreenState,
    viewModel: HomeViewModel
) {
    Scaffold(
        topBar = {
            WeatherTopAppBar(
                titleRes = R.string.app_name,
                modifier = modifier
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->

        SwipeRefresh(
            swipeRefreshState,
            onRefresh = {
                viewModel.loadWeatherInfo()
            },
            indicatorPadding = paddingValues
        ) {
            LazyColumn(
                contentPadding = paddingValues,
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    uiState.weatherInfo?.currentWeatherData?.let {
                        HomeHeader(
                            location = "Your location",
                            lastFetchTime = it.time.toString(),
                            img = it.weatherType.iconRes
                        )
                    }
                }
            }
        }
    }
}