package com.lyvetech.cloudy.common.utils

import java.text.SimpleDateFormat
import java.util.Locale

fun String.toDateFormat(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
    val outputFormat = SimpleDateFormat("EEEE MMM d, hh:mm a", Locale.ENGLISH)
    val inputDate = inputFormat.parse(this)
    return outputFormat.format(inputDate)
}