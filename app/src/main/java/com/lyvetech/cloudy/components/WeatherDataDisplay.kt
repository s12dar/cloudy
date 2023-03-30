package com.lyvetech.cloudy.components

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lyvetech.cloudy.R
import com.lyvetech.cloudy.core.theme.CloudyTheme

@Composable
fun WeatherDataDisplay(
    modifier: Modifier = Modifier,
    humidity: Double,
    pressure: Double,
    windSpeed: Double
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        WeatherDataElement(
            icon = R.drawable.ic_humidity,
            text = R.string.weather_humidity,
            value = "${humidity}%"
        )
        WeatherDataElement(
            icon = R.drawable.ic_pressure,
            text = R.string.weather_pressure,
            value = "${pressure}hPa"
        )
        WeatherDataElement(
            icon = R.drawable.ic_speed,
            text = R.string.weather_wind_speed,
            value = "${windSpeed}km/h"
        )
    }
}

@Composable
fun WeatherDataElement(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    @StringRes text: Int,
    value: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = null
        )
        Text(
            text = stringResource(id = text),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.paddingFromBaseline(
                top = 24.dp, bottom = 8.dp
            )
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.paddingFromBaseline(
                top = 8.dp, bottom = 8.dp
            )
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun WeatherDataElementPreview() {
    CloudyTheme {
        WeatherDataElement(
            icon = R.drawable.ic_speed,
            text = R.string.weather_humidity,
            value = "33.0%"
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun WeatherDataDisplayPreview() {
    CloudyTheme {
        WeatherDataDisplay(
            modifier = Modifier,
            humidity = 12.0,
            pressure = 12.0,
            windSpeed = 12.0
        )
    }
}