package com.example.cloudy.features.home.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.cloudy.features.home.ui.HomeScreen
import com.lyvetech.core.navigation.CloudyNavDestination

object HomeScreenDestination : CloudyNavDestination {
    override val route: String = "home_route"
}

//@RequiresApi(Build.VERSION_CODES.O)
//fun NavGraphBuilder.homeGraph() {
//    composable(route = HomeScreenDestination.route) {
//        HomeScreen()
//    }
//}