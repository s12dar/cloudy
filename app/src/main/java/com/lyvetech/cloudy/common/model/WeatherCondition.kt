package com.lyvetech.cloudy.common.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherCondition(
    var temp: Double,
    val pressure: Double,
    val humidity: Double
) : Parcelable