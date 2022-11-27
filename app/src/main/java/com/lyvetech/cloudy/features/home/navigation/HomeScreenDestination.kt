package com.lyvetech.cloudy.features.home.navigation

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