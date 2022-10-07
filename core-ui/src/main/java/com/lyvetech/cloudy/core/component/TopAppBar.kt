package com.lyvetech.cloudy.core.component

import androidx.annotation.StringRes
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun WeatherTopAppBar(
    @StringRes titleRes: Int,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(text = stringResource(id = titleRes)) },
        modifier = modifier
    )
}