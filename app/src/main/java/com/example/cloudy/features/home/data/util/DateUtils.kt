package com.example.cloudy.features.home.data.util

import android.annotation.SuppressLint
import com.example.cloudy.features.home.domain.model.WeatherInfo
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun WeatherInfo.formatDate(): String {
    val date = Date(lastFetchedTime)
    val dateFormat = SimpleDateFormat("EEEE MMM d, hh:mm aaa")
    return dateFormat.format(date)
}