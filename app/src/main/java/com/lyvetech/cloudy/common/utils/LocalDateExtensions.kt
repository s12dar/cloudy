package com.lyvetech.cloudy.common.utils

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun MutableState<LocalDate>.getDifferences(fromDate: LocalDate): Int {
    val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val from = LocalDate.parse(fromDate.toString(), dateFormatter)
    val to = LocalDate.parse(this.value.toString(), dateFormatter)

    return Period.between(from, to).days
}