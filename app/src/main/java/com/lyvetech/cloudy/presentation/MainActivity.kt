package com.lyvetech.cloudy.presentation

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.lyvetech.cloudy.data.pref.ThemeSelection
import com.facebook.stetho.Stetho
import com.lyvetech.cloudy.common.ui.theme.CloudyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { }
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )

        Stetho.initializeWithDefaults(this)
        setContent {
            val viewModel: MainViewModel = hiltViewModel()
            val appPreferences = viewModel.appPreferences.observeAsState()

            appPreferences.value?.let { preferences ->
                val darkTheme = when (preferences.selectedTheme) {
                    ThemeSelection.SYSTEM -> isSystemInDarkTheme()
                    ThemeSelection.LIGHT -> false
                    ThemeSelection.DARK -> true
                }
                CloudyTheme(darkTheme = darkTheme) {
                    MainContent()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
}