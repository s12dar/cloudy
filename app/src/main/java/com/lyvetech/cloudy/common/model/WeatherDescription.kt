package com.lyvetech.cloudy.common.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherDescription(
    val id: Long,
    val main: String?,
    val description: String?,
    val icon: String?
) : Parcelable