package com.lyvetech.cloudy.common.utils

import android.location.Address
import android.location.Geocoder
import com.lyvetech.cloudy.CloudyApplication
import com.lyvetech.cloudy.common.model.LocationModel
import java.util.Locale

fun LocationModel.toReadableLocation(): String {
    val appContext = CloudyApplication.context
    val geocoder = Geocoder(appContext, Locale.getDefault())
    val addresses =
        geocoder.getFromLocation(this.latitude, this.longitude, 1) as List<Address>
    val cityName = addresses[0].getAddressLine(0)
        .split(",")
        .toTypedArray()

    return cityName[1]
}