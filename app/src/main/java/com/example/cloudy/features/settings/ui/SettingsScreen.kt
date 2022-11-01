package com.example.cloudy.features.settings.ui

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cloudy.R
import com.lyvetech.cloudy.core.theme.CloudyTheme
import com.lyvetech.cloudy.core.theme.typography

@Composable
fun SettingsScreen(
    modifier: Modifier
) {
    Surface {
        Column(modifier = Modifier.fillMaxSize()) {
            GeneralSettingsSection(title = R.string.general_settings) {
                GeneralSettingsContent(modifier = modifier)
            }
            OtherSettingsSection(title = R.string.other_settings) {
                OtherSettingsContent(modifier = modifier)
            }
        }
    }
}

@ComposeCompilerApi
@Composable
fun GeneralSettingsSection(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier = modifier) {
        Text(
            stringResource(id = title),
            style = MaterialTheme.typography.h2,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                .padding(horizontal = 16.dp),
        )
        content()
    }
}

@Composable
fun OtherSettingsSection(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier = modifier) {
        Text(
            stringResource(id = title),
            style = MaterialTheme.typography.h2,
            modifier = Modifier
                .paddingFromBaseline(top = 16.dp, bottom = 16.dp)
                .padding(horizontal = 16.dp),
        )
        content()
    }
}

@Composable
fun GeneralSettingsContent(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        SettingsItem(
            title = R.string.theme,
            value = R.string.theme_dark,
            icon = Icons.Filled.DarkMode
        )
        SettingsItem(
            title = R.string.temperature_unit,
            value = R.string.theme_dark,
            icon = Icons.Filled.Cloud
        )
    }
}

@Composable
fun OtherSettingsContent(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        SettingsItem(
            title = R.string.share_application,
            value = R.string.invite_friends,
            icon = Icons.Filled.Share
        )
        SettingsItem(
            title = R.string.report_issue,
            value = R.string.help_us,
            icon = Icons.Filled.BugReport
        )
        SettingsItem(
            title = R.string.rate_us,
            value = R.string.give_feedbacks,
            icon = Icons.Filled.Star
        )
        SettingsItem(
            title = R.string.version,
            value = R.string.app_version,
            icon = Icons.Filled.Code
        )
    }
}

@Composable
fun SettingsItem(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    @StringRes value: Int,
    icon: ImageVector
) {
    Row(
        modifier = Modifier
            .clickable(onClick = {})
            .fillMaxWidth()
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.padding(16.dp)
        )
        Column(
            modifier = Modifier.padding(
                vertical = 16.dp
            )
        ) {
            Text(
                text = stringResource(id = title),
                style = typography.h3,
                modifier = modifier
            )
            Text(
                text = stringResource(id = value),
                style = typography.body1,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SettingsItemPreview() {
    CloudyTheme {
        SettingsItem(
            title = R.string.temperature_unit,
            value = R.string.unit_fahrenheit,
            icon = Icons.Filled.DarkMode
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun WeatherDataElementPreview() {
    SettingsScreen(
        modifier = Modifier
    )
}