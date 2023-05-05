package com.lyvetech.cloudy.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.lyvetech.cloudy.common.model.City
import com.lyvetech.cloudy.common.model.WeatherCondition
import com.lyvetech.cloudy.common.model.WeatherDescription
import com.lyvetech.cloudy.common.model.Wind

data class WeatherForecastDto(
    @SerializedName("list")
    val weathers: List<WeatherForecastItem>,
    val city: City
)

data class WeatherForecastItem(
    val id: Int,
    @SerializedName("dt_txt")
    val date: String,
    val wind: Wind,
    @SerializedName("weather")
    val weatherDescription: List<WeatherDescription>,
    @SerializedName("main")
    val weatherCondition: WeatherCondition
)