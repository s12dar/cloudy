package com.lyvetech.cloudy.presentation.home.components

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lyvetech.cloudy.R

@Composable
fun HomeBody(
    modifier: Modifier = Modifier,
    location: String,
    lastFetchTime: String,
    temperature: String,
    weatherType: String,
    @DrawableRes img: Int
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = location,
            style = MaterialTheme.typography.headlineLarge
        )
        Text(
            text = lastFetchTime,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.paddingFromBaseline(
                top = 24.dp, bottom = 8.dp
            )
        )

        Image(
            modifier = modifier
                .size(120.dp),
            painter = painterResource(id = img),
            contentDescription = null,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
        )

        Text(
            text = temperature,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = weatherType,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.paddingFromBaseline(
                top = 24.dp, bottom = 8.dp
            )
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HomeHeaderPreview() {
    HomeBody(
        modifier = Modifier.fillMaxWidth(),
        location = "San Francisco, CA",
        lastFetchTime = "Wednesday, Oct 5, 09:15 PM",
        img = R.drawable.ic_storm,
        temperature = "13.55",
        weatherType = "Clear Sky"
    )
}