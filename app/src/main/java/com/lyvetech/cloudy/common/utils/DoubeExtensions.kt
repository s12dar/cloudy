package com.lyvetech.cloudy.common.utils

import java.text.DecimalFormat

fun Double.toFahrenheit(): Double {
    return DecimalFormat().run {
        applyPattern(".##")
        this.parse(format(this@toFahrenheit.times(1.8).plus(32)))!!.toDouble()
    }
}

fun Double.toCelsius(): Double {
    return DecimalFormat().run {
        applyPattern(".##")
        this.parse(format(this@toCelsius - 273.15))!!.toDouble()
    }
}