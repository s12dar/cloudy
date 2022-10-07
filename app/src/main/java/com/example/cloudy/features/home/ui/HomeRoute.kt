package com.example.cloudy.features.home.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cloudy.R
import com.example.cloudy.components.HomeBody
import com.example.cloudy.components.WeatherDataDisplay
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.lyvetech.cloudy.core.component.WeatherTopAppBar
import kotlin.math.roundToInt

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
    viewModel: HomeViewModel
) {
    Scaffold(
        topBar = {
            WeatherTopAppBar(
                titleRes = R.string.app_name, modifier = modifier
            )
        }, modifier = Modifier.fillMaxSize()
    ) { paddingValues ->

        SwipeRefresh(
            swipeRefreshState, onRefresh = {
                viewModel.loadWeatherInfo()
            }, indicatorPadding = paddingValues
        ) {
            uiState.weatherInfo?.currentWeatherData?.let {

                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    HomeBody(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp, start = 8.dp, end = 8.dp),
                        location = "Your location",
                        lastFetchTime = it.time.toString(),
                        img = it.weatherType.iconRes
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        WeatherDataDisplay(
                            value = it.pressure.roundToInt(),
                            unit = "hpa",
                            icon = ImageVector.vectorResource(id = R.drawable.ic_storm),
                        )

                        WeatherDataDisplay(
                            value = it.humidity.roundToInt(),
                            unit = "%",
                            icon = ImageVector.vectorResource(id = R.drawable.ic_storm)
                        )

                        WeatherDataDisplay(
                            value = it.windSpeed.roundToInt(),
                            unit = "km/h",
                            icon = ImageVector.vectorResource(id = R.drawable.ic_storm)
                        )
                    }
                }
            }
        }
    }
}