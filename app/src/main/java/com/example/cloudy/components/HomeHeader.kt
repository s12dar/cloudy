package com.example.cloudy.components

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.Image
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.cloudy.R

@Composable
fun HomeHeader(
    modifier: Modifier = Modifier,
    location: String,
    lastFetchTime: String,
    @DrawableRes img: Int
) {
    ConstraintLayout(modifier = modifier) {
        val (textLocation, textLastFetchTime, imageWeatherType) = createRefs()

        Text(
            text = location,
            style = typography.h4,
            modifier = Modifier
                .constrainAs(textLocation) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                })

        Text(
            text = lastFetchTime,
            style = typography.subtitle2,
            modifier = Modifier
                .constrainAs(textLastFetchTime) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(textLocation.bottom)
                }
                .padding(top = 16.dp)
        )

        Image(
            painter = painterResource(id = img),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(imageWeatherType) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(textLastFetchTime.bottom)
                }
        )
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HomeHeaderPreview() {
    HomeHeader(
        modifier = Modifier.fillMaxWidth(),
        location = "San Francisco, CA",
        lastFetchTime = "Wednesday, Oct 5",
        img = R.drawable.ic_storm
    )
}