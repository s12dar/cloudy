package com.example.cloudy.features.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.cloudy.features.home.ui.HomeRoute
import com.lyvetech.core.navigation.CloudyNavDestination

object HomeScreenDestination : CloudyNavDestination {
    override val route: String = "home_route"
}

fun NavGraphBuilder.homeGraph() {
    composable(route = HomeScreenDestination.route) {
        HomeRoute()
    }
}