package com.lyvetech.cloudy

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CloudyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}