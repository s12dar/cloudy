package com.lyvetech.cloudy.domain.service

import com.lyvetech.cloudy.common.model.LocationModel
import com.lyvetech.cloudy.domain.util.Resource

interface LocationTracker {
    suspend fun getCurrentLocation(): Resource<LocationModel?>
}