package com.lyvetech.cloudy.data.mapper

import com.lyvetech.cloudy.data.local.entity.WeatherEntity
import com.lyvetech.cloudy.data.local.entity.WeatherForecastEntity
import com.lyvetech.cloudy.data.remote.dto.WeatherDto
import com.lyvetech.cloudy.data.remote.dto.WeatherForecastItem
import com.lyvetech.cloudy.domain.model.Weather
import com.lyvetech.cloudy.domain.model.WeatherForecast

//fun WeatherDto.transformDtoToDomain() = Weather(
//    uId = this.uId,
//    cityId = this.cityId,
//    name = this.name,
//    wind = this.wind,
//    networkWeatherDescription = this.networkWeatherDescriptions,
//    networkWeatherCondition = this.networkWeatherCondition
//)

//fun List<WeatherForecastItem>.transformDtoToDomain(): List<WeatherForecast> {
//    return this.map { networkWeatherForecast ->
//        WeatherForecast(
//            networkWeatherForecast.id,
//            networkWeatherForecast.date,
//            networkWeatherForecast.wind,
//            networkWeatherForecast.networkWeatherDescription,
//            networkWeatherForecast.networkWeatherCondition
//        )
//    }
//}

fun WeatherDto.transformDtoToEnt(lastFetchTime: Long) = WeatherEntity(
    uId = this.uId,
    cityId = this.cityId,
    cityName = this.name,
    wind = this.wind,
    weatherDescription = this.weatherDescriptions,
    weatherCondition = this.weatherCondition,
    lastFetchTime = lastFetchTime
)

fun List<WeatherForecastItem>.transformDtoToEntity(): List<WeatherForecastEntity> {
    return this.map { weatherForecastDto ->
        WeatherForecastEntity(
            weatherForecastDto.id,
            weatherForecastDto.date,
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

fun List<WeatherForecast>.transformDomainToEnt(): List<WeatherForecastEntity> {
    return this.map { weatherForecast ->
        WeatherForecastEntity(
            weatherForecast.uID,
            weatherForecast.date,
            weatherForecast.wind,
            weatherForecast.networkWeatherDescription,
            weatherForecast.networkWeatherCondition
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

fun Weather.transformDomainToEnt() = WeatherEntity(
    uId = this.uId,
    cityId = this.cityId,
    cityName = this.name,
    wind = this.wind,
    lastFetchTime = this.lastFetchedTime,
    weatherDescription = this.networkWeatherDescription,
    weatherCondition = this.networkWeatherCondition
)