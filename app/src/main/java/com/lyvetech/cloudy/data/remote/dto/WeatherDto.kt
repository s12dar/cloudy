package com.lyvetech.cloudy.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.lyvetech.cloudy.common.model.WeatherCondition
import com.lyvetech.cloudy.common.model.WeatherDescription
import com.lyvetech.cloudy.common.model.Wind

data class WeatherDto(
    val uId: Int,
    @SerializedName("id")
    val cityId: Int,
    val name: String,
    val wind: Wind,
    @SerializedName("weather")
    val weatherDescriptions: List<WeatherDescription>,
    @SerializedName("main")
    val weatherCondition: WeatherCondition
)