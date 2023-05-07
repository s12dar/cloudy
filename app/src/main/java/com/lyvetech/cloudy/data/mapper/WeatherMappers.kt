package com.lyvetech.cloudy.data.mapper

import com.lyvetech.cloudy.data.local.entity.WeatherEntity
import com.lyvetech.cloudy.data.local.entity.WeatherForecastEntity
import com.lyvetech.cloudy.data.remote.dto.WeatherDto
import com.lyvetech.cloudy.data.remote.dto.WeatherForecastDto
import com.lyvetech.cloudy.domain.model.Weather
import com.lyvetech.cloudy.domain.model.WeatherForecast
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


fun WeatherDto.transformDtoToEnt(lastFetchTime: Long) = WeatherEntity(
    uId = this.uId,
    cityId = this.cityId,
    cityName = this.name,
    wind = this.wind,
    weatherDescription = this.weatherDescriptions,
    weatherCondition = this.weatherCondition,
    lastFetchTime = lastFetchTime
)

fun List<WeatherForecastDto>.transformDtoToEntity(lastFetchTime: Long): List<WeatherForecastEntity> {
    return this.map { weatherForecastDto ->
        WeatherForecastEntity(
            weatherForecastDto.id,
            weatherForecastDto.date,
            lastFetchTime,
            weatherForecastDto.wind,
            weatherForecastDto.weatherDescription,
            weatherForecastDto.weatherCondition
        )
    }
}

fun List<WeatherForecastEntity>.transformEntToDomain(): List<WeatherForecast> {
    return this.map { weatherForecastEntity ->
        WeatherForecast(
            weatherForecastEntity.id,
            weatherForecastEntity.date,
            weatherForecastEntity.wind,
            weatherForecastEntity.networkWeatherDescriptions,
            weatherForecastEntity.networkWeatherCondition
        )
    }
}

fun WeatherEntity.transformEntToDomain(): Weather = Weather(
    uId = this.uId,
    cityId = this.cityId,
    name = this.cityName,
    wind = this.wind,
    networkWeatherDescription = this.weatherDescription,
    networkWeatherCondition = this.weatherCondition,
    lastFetchedTime = this.lastFetchTime
)
fun List<WeatherForecast>.filterWeatherForecastsByDay(selectedDay: Int): List<WeatherForecast> {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DATE, selectedDay)
    val checkerDay = calendar.get(Calendar.DATE)
    val checkerMonth = calendar.get(Calendar.MONTH)
    val checkerYear = calendar.get(Calendar.YEAR)

    val filteredList = this.filter { weatherForecast ->
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
        val formattedDate = format.parse(weatherForecast.date)
        val weatherForecastDay = formattedDate?.date
        val weatherForecastMonth = formattedDate?.month
        val weatherForecastYear = formattedDate?.year

        // This checks if the selected day, month and year are equal. The year requires an addition of 1900 to get the correct year.
        weatherForecastDay == checkerDay && weatherForecastMonth == checkerMonth && weatherForecastYear?.plus(
            1900
        ) == checkerYear
    }

    return filteredList
}