package com.lyvetech.cloudy.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.lyvetech.cloudy.R
import com.lyvetech.cloudy.common.navigation.CloudyNavGraph
import com.lyvetech.cloudy.common.navigation.NavDestinations
import com.lyvetech.cloudy.common.ui.compose.FeedbackIconButton

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainContent() {
    val navController = rememberAnimatedNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { CloudyBottomBar(navController) },
        topBar = {
            if (getCurrentTopLevelDestination(navController) != null) CloudyTopAppBar(
                navController
            )
        }
    ) {
        Column {
            CloudyNavGraph(
                navController = navController,
                modifier = Modifier.padding(it)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CloudyTopAppBar(
    navController: NavHostController
) {
    CenterAlignedTopAppBar(
        title = {},
        scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
        actions = {
            FeedbackIconButton(
                imageVector = Icons.Outlined.Settings,
                contentDescription = stringResource(R.string.settings),
                tint = MaterialTheme.colorScheme.onSurface,
            ) {
                navController.navigate(NavDestinations.Screen.Settings.route)
            }
        }
    )
}

@Composable
private fun CloudyBottomBar(
    navController: NavHostController
) {
    val screens = listOf(NavDestinations.Screen.Home, NavDestinations.Screen.Forecast)
    val selectedTab = remember { mutableStateOf(0) }

    NavigationBar(
        modifier = Modifier
            .navigationBarsPadding(),
        containerColor = Color.Transparent,
    ) {
        screens.forEachIndexed { index, screen ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = requireNotNull(screen.icon),
                        contentDescription = null
                    )
                },
                label = { Text(text = screen.title) },
                selected = index == selectedTab.value,
                alwaysShowLabel = false,
                onClick = {
                    selectedTab.value = index
                    navController.navigate(screen.route) { popUpTo(0) }
                },
                modifier = Modifier.navigationBarsPadding()
            )
        }
    }
}

@Composable
private fun getCurrentDestination(navController: NavHostController): NavDestination? {
    return navController.currentBackStackEntryAsState().value?.destination
}

@Composable
private fun getCurrentTopLevelDestination(navController: NavHostController): NavDestinations? {
    return when (getCurrentDestination(navController = navController)?.route) {
        "home" -> NavDestinations.Screen.Home
        "forecast" -> NavDestinations.Screen.Forecast
        else -> null
    }
}