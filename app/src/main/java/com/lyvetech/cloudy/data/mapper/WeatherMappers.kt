package com.lyvetech.cloudy.data.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.lyvetech.cloudy.common.model.LocationModel
import com.lyvetech.cloudy.data.local.entity.WeatherDataEntity
import com.lyvetech.cloudy.data.local.entity.WeatherEntity
import com.lyvetech.cloudy.data.remote.dto.WeatherDataDto
import com.lyvetech.cloudy.data.remote.dto.WeatherDto
import com.lyvetech.cloudy.domain.model.WeatherData
import com.lyvetech.cloudy.domain.model.WeatherInfo
import com.lyvetech.cloudy.domain.model.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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
fun WeatherEntity.toWeatherInfo(): WeatherInfo {
    val weatherDataMap = weatherData.toWeatherDataMap()
    val now = LocalDateTime.now()
    val location = LocationModel(latitude, longitude)
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