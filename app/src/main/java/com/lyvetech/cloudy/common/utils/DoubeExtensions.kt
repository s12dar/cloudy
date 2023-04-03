package com.lyvetech.cloudy.common.utils

import java.text.DecimalFormat

fun Double.toFahrenheit(): Double {
    return DecimalFormat().run {
        applyPattern(".##")
        parse(format(this@toFahrenheit.times(1.8).plus(32))).toDouble()
    }
}