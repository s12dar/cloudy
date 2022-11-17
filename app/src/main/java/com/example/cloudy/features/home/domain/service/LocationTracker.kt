package com.example.cloudy.features.home.domain.service

import android.location.Location
import com.example.cloudy.core.domain.model.LocationModel
import com.example.cloudy.core.util.Resource

interface LocationTracker {
    suspend fun getCurrentLocation(): Resource<LocationModel?>
}