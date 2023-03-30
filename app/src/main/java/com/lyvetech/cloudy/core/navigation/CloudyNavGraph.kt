package com.lyvetech.cloudy.features.home.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lyvetech.cloudy.features.home.ui.HomeRoute
import com.lyvetech.cloudy.features.settings.ui.SettingsRoute

@Composable
fun CloudyNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = "home",
        builder = {
            homeScreen(navController, modifier = modifier)
            settingsScreen(navController, modifier = modifier)
        }
    )
}

@SuppressLint("NewApi")
fun NavGraphBuilder.homeScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    composable(
        route = NavDestinations.Screen.Home.route
    ) {
        HomeRoute()
    }
}

fun NavGraphBuilder.settingsScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    composable(
        route = NavDestinations.Screen.Settings.route
    ) {
        SettingsRoute()
    }
}