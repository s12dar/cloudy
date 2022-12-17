package com.lyvetech.cloudy.features.home.data.util

import android.location.Address
import android.location.Geocoder
import com.lyvetech.cloudy.WeatherApplication
import com.lyvetech.cloudy.core.domain.model.LocationModel
import java.util.Locale

fun manageLocation(
    location: LocationModel
): String {
    val appContext = WeatherApplication.context
    val geocoder = Geocoder(appContext, Locale.getDefault())
    val addresses =
        geocoder.getFromLocation(location.latitude, location.longitude, 1) as List<Address>
    val cityName = addresses[0].getAddressLine(0)
        .split(",")
        .toTypedArray()

    return cityName[1]
}