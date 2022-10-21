package com.example.cloudy.features.home.data.mapper

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.cloudy.features.home.data.local.entity.WeatherDataEntity
import com.example.cloudy.features.home.data.local.entity.WeatherEntity
import com.example.cloudy.features.home.data.remote.dto.WeatherDataDto
import com.example.cloudy.features.home.data.remote.dto.WeatherDto
import com.example.cloudy.features.home.domain.model.WeatherData
import com.example.cloudy.features.home.domain.model.WeatherInfo
import com.example.cloudy.features.home.domain.model.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

private data class IndexedWeatherData(
    val index: Int,
    val data: WeatherData
)

@RequiresApi(Build.VERSION_CODES.O)
private fun WeatherDataEntity.toWeatherDataMap(): Map<Int, List<WeatherData>> {
    return time.mapIndexed { index, value ->
        val temperature = temperatures[index]
        val weatherCode = weatherCodes[index]
        val windSpeed = windSpeeds[index]
        val pressure = pressures[index]
        val humidity = humidities[index]
        IndexedWeatherData(
            index = index,
            WeatherData(
                time = LocalDateTime.parse(value, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius = temperature,
                windSpeed = windSpeed,
                pressure = pressure,
                humidity = humidity,
                weatherType = WeatherType.fromWMO(weatherCode)
            )
        )
    }.groupBy {
        it.index / 24
    }.mapValues {
        it.value.map { indexedWeatherData ->
            indexedWeatherData.data
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun WeatherEntity.toWeatherInfo(context: Context): WeatherInfo {
    val weatherDataMap = weatherData.toWeatherDataMap()
    val now = LocalDateTime.now()
    val location = manageLocation(context, latitude, longitude)
    val currentWeatherData = weatherDataMap[0]?.find {
        val hour = if (now.minute < 30) now.hour else now.hour + 1
        it.time.hour == hour
    }

    return WeatherInfo(
        weatherDataPerDay = weatherDataMap,
        currentWeatherData = currentWeatherData,
        location = location,
        lastFetchedTime = lastFetchTime
    )
}

private fun WeatherEntity.manageLocation(
    context: Context,
    latitude: Double,
    longitude: Double
): String {
    val geocoder = Geocoder(context, Locale.getDefault())
    val addresses = geocoder.getFromLocation(latitude, longitude, 1) as List<Address>
    val cityName = addresses[0].getAddressLine(0)
        .split(",")
        .toTypedArray()

    return cityName[1]
}

fun WeatherDto.toWeatherLocal(lastFetchTime: Long): WeatherEntity {
    val weatherDataLocal = weatherData.toWeatherDataLocal()
    return WeatherEntity(
        lastFetchTime = lastFetchTime,
        weatherData = weatherDataLocal,
        latitude = latitude,
        longitude = longitude
    )
}

fun WeatherDataDto.toWeatherDataLocal(): WeatherDataEntity {
    return WeatherDataEntity(
        time = time,
        temperatures = temperatures,
        weatherCodes = weatherCodes,
        windSpeeds = windSpeeds,
        pressures = pressures,
        humidities = humidities
    )
}