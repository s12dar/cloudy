package com.lyvetech.cloudy.features.settings.ui

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lyvetech.cloudy.R
import com.lyvetech.cloudy.components.LabelledRadioButton
import com.lyvetech.cloudy.core.theme.CloudyTheme
import com.lyvetech.cloudy.core.theme.typography
import com.lyvetech.cloudy.core.util.Constants.APP_URL
import com.lyvetech.cloudy.core.util.Constants.CLOUDY_EMAIL
import com.lyvetech.cloudy.features.settings.data.datastore.TempUnitSelection
import com.lyvetech.cloudy.features.settings.data.datastore.ThemeSelection

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.observeAsState(SettingsScreenState.initialState)
    val actions: SettingsScreenActions = viewModel
    val context = LocalContext.current
    val selectedTheme = uiState.value.appPreferences.selectedTheme
    val selectedTempUnit = uiState.value.appPreferences.selectedTempUnit

    if (uiState.value.showThemeDialog) {
        SingleChoiceDialog(
            title = stringResource(id = R.string.theme),
            options = ThemeSelection.values().toList().map { stringResource(id = it.readableName) },
            initialSelectedOptionIndex = ThemeSelection.values()
                .indexOf(selectedTheme),
            onConfirmed = { index ->
                actions.onThemeUpdated(ThemeSelection.values()[index])
            },
            onDismissRequest = { actions.onThemeDialogDismissed() }
        )
    }

    if (uiState.value.showTempUnitDialog) {
        SingleChoiceDialog(
            title = stringResource(id = R.string.temperature_unit),
            options = TempUnitSelection.values().toList()
                .map { stringResource(id = it.readableName) },
            initialSelectedOptionIndex = TempUnitSelection.values().indexOf(selectedTempUnit),
            onConfirmed = { index ->
                actions.onTempUnitUpdated(TempUnitSelection.values()[index])
            },
            onDismissRequest = { actions.onTempUnitDialogDismissed() }
        )
    }

    Column(modifier = Modifier.fillMaxSize()) {
        GeneralSettingsSection(
            title = R.string.general_settings,
            modifier = modifier
        ) {
            SettingsItem(
                title = R.string.theme,
                value = selectedTheme.readableName,
                icon = Icons.Filled.DarkMode,
                onClick = { actions.onThemePreferenceClicked() }
            )
            SettingsItem(
                title = R.string.temperature_unit,
                value = selectedTempUnit.readableName,
                icon = Icons.Filled.Cloud,
                onClick = { actions.onTempUnitPreferenceClicked() }
            )
        }
        OtherSettingsSection(
            title = R.string.other_settings,
            modifier = modifier
        ) {
            SettingsItem(
                title = R.string.share_application,
                value = R.string.invite_friends,
                icon = Icons.Filled.Share
            ) {
                context.startActivity(
                    Intent.createChooser(
                        Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(
                                Intent.EXTRA_TEXT,
                                "Heyy there! Check the Cloudy out on Google Play; $APP_URL"
                            )
                            type = "text/plain"
                        },
                        null
                    )
                )
            }

            SettingsItem(
                title = R.string.report_issue,
                value = R.string.help_us,
                icon = Icons.Filled.BugReport
            ) {
                context.startActivity(
                    Intent.createChooser(
                        Intent(Intent.ACTION_SENDTO).apply {
                            data = Uri.parse("mailto:$CLOUDY_EMAIL")
                        },
                        null
                    )
                )
            }

            SettingsItem(
                title = R.string.rate_us,
                value = R.string.give_feedbacks,
                icon = Icons.Filled.Star
            ) {
                context.startActivity(
                    Intent.createChooser(
                        Intent(Intent.ACTION_VIEW, Uri.parse(APP_URL)),
                        null
                    )
                )
            }

            SettingsItem(
                title = R.string.version,
                value = R.string.app_version,
                icon = Icons.Filled.Code,
                onClick = { }
            )
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
            text = stringResource(id = title),
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
fun SettingsItem(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    @StringRes value: Int,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .clickable(onClick = onClick)
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

@Composable
private fun SingleChoiceDialog(
    title: String,
    options: List<String>,
    initialSelectedOptionIndex: Int,
    modifier: Modifier = Modifier,
    onConfirmed: (index: Int) -> Unit,
    onDismissRequest: () -> Unit
) {
    val selectedOptionIndex = rememberSaveable { mutableStateOf(initialSelectedOptionIndex) }

    AlertDialog(
        modifier = Modifier.clip(RoundedCornerShape(24.dp)),
        onDismissRequest = onDismissRequest,
        title = { Text(title) },
        text = {
            Column {
                options.forEach { option ->
                    LabelledRadioButton(
                        text = option,
                        selected = selectedOptionIndex.value == options.indexOf(option),
                        onClick = { selectedOptionIndex.value = options.indexOf(option) }
                    )
                }
            }
        },
        confirmButton = {
            TextButton(onClick = { onConfirmed(selectedOptionIndex.value) }) {
                Text(stringResource(id = R.string.ok))
            }
        }
    )
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SettingsItemPreview() {
    CloudyTheme {
        SettingsItem(
            title = R.string.theme,
            value = R.string.system_default,
            icon = Icons.Filled.DarkMode,
            onClick = {}
        )
    }
}