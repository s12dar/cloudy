package com.example.cloudy.features.weather.data.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.cloudy.features.weather.data.remote.dto.WeatherDataDto
import com.example.cloudy.features.weather.data.remote.dto.WeatherDto
import com.example.cloudy.features.weather.domain.model.WeatherData
import com.example.cloudy.features.weather.domain.model.WeatherInfo
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


private data class IndexedWeatherData(
    val index: Int,
    val data: WeatherData
)

@RequiresApi(Build.VERSION_CODES.O)
fun WeatherDataDto.toWeatherDataMap(): Map<Int, List<WeatherData>> {
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
                temperatureInCelsius = temperature,
                windSpeed = windSpeed,
                pressure = pressure,
                humidity = humidity
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
fun WeatherDto.toWeatherInfo(): WeatherInfo {
    val weatherDataMap = weatherData.toWeatherDataMap()
    val now = LocalDateTime.now()
    val currentWeatherData = weatherDataMap[0]?.find {
        val hour = if (now.minute < 30) now.hour else now.hour + 1
        it.time.hour == hour
    }

    return WeatherInfo(
        weatherInfoPerDay = weatherDataMap,
        currentWeatherData = currentWeatherData
    )
}