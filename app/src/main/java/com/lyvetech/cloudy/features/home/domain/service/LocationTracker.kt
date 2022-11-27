package com.lyvetech.cloudy.features.home.domain.service

import com.lyvetech.cloudy.core.domain.model.LocationModel
import com.lyvetech.cloudy.core.util.Resource

interface LocationTracker {
    suspend fun getCurrentLocation(): Resource<LocationModel?>
}