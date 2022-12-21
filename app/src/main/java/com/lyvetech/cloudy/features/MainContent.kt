package com.lyvetech.cloudy.features

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.lyvetech.cloudy.R
import com.lyvetech.cloudy.features.home.ui.HomeScreen
import com.lyvetech.cloudy.features.settings.ui.SettingsScreen
import com.lyvetech.cloudy.core.component.WeatherTopAppBar
import com.lyvetech.cloudy.features.home.ui.HomeRoute
import com.lyvetech.cloudy.features.settings.ui.SettingsRoute

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainContent() {
    val (selectedTab, setSelectedTab) = remember { mutableStateOf(CloudyTabs.Home) }
    val tabs = CloudyTabs.values()

    Scaffold(
        topBar = {
            WeatherTopAppBar(
                titleRes = R.string.app_name
            )
        },
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigation {
                tabs.forEach { tab ->
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                imageVector = tab.icon,
                                contentDescription = null
                            )
                        },
                        label = { Text(text = stringResource(id = tab.title)) },
                        selected = tab == selectedTab,
                        alwaysShowLabel = false,
                        selectedContentColor = MaterialTheme.colors.secondary,
                        unselectedContentColor = LocalContentColor.current,
                        onClick = { setSelectedTab(tab) },
                        modifier = Modifier.navigationBarsPadding()
                    )
                }
            }
        }
    ) {
        when (selectedTab) {
            CloudyTabs.Home -> HomeRoute(modifier = Modifier.padding(it))
            CloudyTabs.Settings -> SettingsRoute(
                modifier = Modifier.padding(it)
            )
        }
    }
}

enum class CloudyTabs(
    @StringRes val title: Int,
    val icon: ImageVector
) {
    Home(R.string.bottom_nav_home, Icons.Filled.Home),
    Settings(R.string.bottom_nav_settings, Icons.Filled.Settings),
}