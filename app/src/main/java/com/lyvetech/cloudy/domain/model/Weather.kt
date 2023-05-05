package com.lyvetech.cloudy.domain.model

import android.os.Parcelable
import com.lyvetech.cloudy.common.model.WeatherCondition
import com.lyvetech.cloudy.common.model.WeatherDescription
import com.lyvetech.cloudy.common.model.Wind
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Weather(
    val uId: Int,
    val cityId: Int,
    val name: String,
    val wind: Wind,
    val lastFetchedTime: Long,
    val networkWeatherDescription: List<WeatherDescription>,
    val networkWeatherCondition: WeatherCondition
) : Parcelable
