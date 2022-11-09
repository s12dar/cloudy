package com.example.cloudy.features.settings.data.util

import java.text.DecimalFormat

fun convertKelvinToCelsius(number: Number): Double {
    return DecimalFormat().run {
        applyPattern(".##")
        parse(format(number.toDouble().minus(273))).toDouble()
    }
}

fun convertCelsiusToFahrenheit(celsius: Double): Double {
    return DecimalFormat().run {
        applyPattern(".##")
        parse(format(celsius.times(1.8).plus(32))).toDouble()
    }
}