package com.lyvetech.cloudy.presentation.forecast

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kizitonwose.calendar.compose.WeekCalendar
import com.kizitonwose.calendar.compose.weekcalendar.WeekCalendarState
import com.kizitonwose.calendar.compose.weekcalendar.rememberWeekCalendarState
import com.lyvetech.cloudy.R
import com.lyvetech.cloudy.common.Constants
import com.lyvetech.cloudy.common.utils.displayText
import com.lyvetech.cloudy.common.utils.getDifferences
import com.lyvetech.cloudy.common.utils.toFahrenheit
import com.lyvetech.cloudy.domain.model.WeatherInfo
import com.lyvetech.cloudy.presentation.forecast.components.ForecastItem
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("NewApi")
@Composable
fun ForecastRoute(
    modifier: Modifier = Modifier,
    viewModel: ForecastViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsState()
    val isForecastLoading = uiState.value.isLoading
    val pullRefreshState =
        rememberPullRefreshState(refreshing = isForecastLoading, { viewModel.refreshWeather() })
    val selectedTempUnit = uiState.value.appPreferences.selectedTempUnit.toString()
    val currentDate = remember { LocalDate.now() }
    val startDate = remember { currentDate }
    val endDate = remember { currentDate.plusDays(7) }
    val selection = remember { mutableStateOf(currentDate) }

    val weatherState = rememberWeekCalendarState(
        startDate = startDate,
        endDate = endDate,
        firstVisibleWeekDate = currentDate,
    )

    uiState.value.weatherInfo?.let {
        ForecastScreen(
            modifier = modifier,
            weatherState = weatherState,
            selection = selection,
            weatherInfo = it,
            selectedTempUnit = selectedTempUnit,
            pullRefreshState = pullRefreshState,
            isForecastLoading = isForecastLoading
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
internal fun ForecastScreen(
    modifier: Modifier = Modifier,
    weatherState: WeekCalendarState,
    pullRefreshState: PullRefreshState,
    selection: MutableState<LocalDate>,
    weatherInfo: WeatherInfo,
    isForecastLoading: Boolean,
    selectedTempUnit: String
) {
    val dayNo = selection.getDifferences(LocalDate.now())

    Box(
        modifier = modifier
            .pullRefresh(pullRefreshState)
            .fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = CenterHorizontally
        ) {
            WeekCalendar(
                state = weatherState,
                dayContent = { day ->
                    Day(day.date, isSelected = selection.value == day.date) { clicked ->
                        if (selection.value != clicked) {
                            selection.value = clicked
                        }
                    }
                },
            )

            Spacer(modifier = Modifier.height(22.dp))

            weatherInfo.weatherDataPerDay[dayNo]?.let {
                LazyColumn {
                    itemsIndexed(it) { index, item ->
                        if (index != 0) {
                            Spacer(modifier = Modifier.height(16.dp))
                        }

                        ForecastItem(
                            dateAndTime = item.time.format(dateFormatter2),
                            weatherType = item.weatherType.weatherDesc,
                            temperature =
                            if (selectedTempUnit == Constants.FAHRENHEIT) "${
                                (item.temperatureCelsius.toFahrenheit())
                            }${Constants.FAHRENHEIT_SIGN}" else "${item.temperatureCelsius}${Constants.CELSIUS_SIGN}",
                            weatherIcon = item.weatherType.iconRes
                        )
                    }
                }
            } ?: run {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalAlignment = CenterHorizontally,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_no_weather_info),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                    )

                    if (dayNo < 0) {
                        Text(
                            text = stringResource(id = R.string.past_weather_msg),
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    } else {
                        Text(
                            text = stringResource(id = R.string.future_weather_msg),
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }

        PullRefreshIndicator(
            refreshing = isForecastLoading,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

@SuppressLint("NewApi")
@Composable
private fun Day(date: LocalDate, isSelected: Boolean, onClick: (LocalDate) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onClick(date) },
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier.padding(vertical = 10.dp),
            horizontalAlignment = CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Text(
                text = date.dayOfWeek.displayText(),
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Light,
            )
            Text(
                text = dateFormatter.format(date),
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
            )
        }
        if (isSelected) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(5.dp)
                    .background(color = MaterialTheme.colorScheme.primary)
                    .align(Alignment.BottomCenter),
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
private val dateFormatter = DateTimeFormatter.ofPattern("dd")

@RequiresApi(Build.VERSION_CODES.O)
private val dateFormatter2: DateTimeFormatter = DateTimeFormatter.ofPattern("EEEE MMM d, hh:mm a")