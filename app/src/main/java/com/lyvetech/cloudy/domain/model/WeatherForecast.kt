package com.lyvetech.cloudy.domain.model

import com.lyvetech.cloudy.common.model.WeatherCondition
import com.lyvetech.cloudy.common.model.WeatherDescription
import com.lyvetech.cloudy.common.model.Wind

data class WeatherForecast(
    val uID: Int,
    var date: String,
    val wind: Wind,
    val networkWeatherDescription: List<WeatherDescription>,
    val networkWeatherCondition: WeatherCondition
)