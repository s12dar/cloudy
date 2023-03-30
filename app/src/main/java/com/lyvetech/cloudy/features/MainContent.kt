package com.lyvetech.cloudy.features

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.lyvetech.cloudy.R
import com.lyvetech.cloudy.core.ui.FeedbackIconButton
import com.lyvetech.cloudy.core.navigation.CloudyNavGraph
import com.lyvetech.cloudy.core.navigation.NavDestinations

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainContent() {
    val navController = rememberAnimatedNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { CloudyBottomBar(navController) },
        topBar = { CloudyTopAppBar() }
    ) {
        Column(Modifier.fillMaxSize()) {
            CloudyNavGraph(
                navController = navController,
                modifier = Modifier.padding(it)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CloudyTopAppBar() {
    CenterAlignedTopAppBar(
        title = {},
        scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
        actions = {
            FeedbackIconButton(
                imageVector = Icons.Outlined.Settings,
                contentDescription = stringResource(R.string.settings),
                tint = MaterialTheme.colorScheme.onSurface,
            )
        }
    )
}

@Composable
private fun CloudyBottomBar(
    navController: NavHostController
) {
    val screens = listOf(NavDestinations.Screen.Home, NavDestinations.Screen.Settings)
    val selectedTab = remember { mutableStateOf(0) }

    NavigationBar(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primaryContainer)
            .navigationBarsPadding(),
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