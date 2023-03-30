package com.lyvetech.cloudy.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavDestinations(val route: String, val title: String, val icon: ImageVector? = null) {
    sealed class Screen {
        object Home : NavDestinations("home", "Home", Icons.Outlined.Home)
        object Forecast : NavDestinations("forecast", "Forecast")
        object Settings : NavDestinations("settings", "Settings", Icons.Outlined.Settings)
    }
}
