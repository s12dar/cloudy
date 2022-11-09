package com.example.cloudy.features.settings.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cloudy.R
import com.example.cloudy.components.LabelledRadioButton
import com.example.cloudy.features.settings.data.datastore.TempUnitSelection
import com.example.cloudy.features.settings.data.datastore.ThemeSelection
import com.lyvetech.cloudy.core.theme.typography

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.observeAsState(SettingsScreenState.initialState)
    val actions: SettingsScreenActions = viewModel
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

    Surface {
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
                    icon = Icons.Filled.Share,
                    onClick = { }
                )
                SettingsItem(
                    title = R.string.report_issue,
                    value = R.string.help_us,
                    icon = Icons.Filled.BugReport,
                    onClick = { }
                )
                SettingsItem(
                    title = R.string.rate_us,
                    value = R.string.give_feedbacks,
                    icon = Icons.Filled.Star,
                    onClick = { }
                )
                SettingsItem(
                    title = R.string.version,
                    value = R.string.app_version,
                    icon = Icons.Filled.Code,
                    onClick = { }
                )
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
        modifier = modifier,
        confirmButton = {
            TextButton(onClick = { onConfirmed(selectedOptionIndex.value) }) {
                Text(stringResource(id = R.string.ok))
            }
        }
    )
}