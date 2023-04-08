package com.lyvetech.cloudy.presentation.forecast.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lyvetech.cloudy.R
import com.lyvetech.cloudy.common.ui.compose.Shape32

@OptIn(androidx.compose.foundation.ExperimentalFoundationApi::class)
@Composable
fun ForecastItem(
    modifier: Modifier = Modifier,
    dateAndTime: String,
    weatherType: String,
    temperature: String,
    @DrawableRes weatherIcon: Int,
) {
    Row(
        modifier = Modifier
            .animateContentSize()
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(Shape32)
            .background(MaterialTheme.colorScheme.primaryContainer),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = temperature,
                style = MaterialTheme.typography.titleLarge,
            )

            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = weatherType,
                style = MaterialTheme.typography.bodyMedium,
            )

            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = dateAndTime,
                style = MaterialTheme.typography.bodySmall,
            )
        }

        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.End
        ) {
            Image(
                modifier = Modifier
                    .size(96.dp),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                painter = painterResource(id = weatherIcon),
                contentDescription = null,
            )
        }
    }
}

@Preview
@Composable
private fun ForecastItemPreview() {
    ForecastItem(
        dateAndTime = "8 Apr 2023, 06:00AM",
        weatherType = "Cloudy",
        temperature = "13.55",
        weatherIcon = R.drawable.ic_snowy,
    )
}