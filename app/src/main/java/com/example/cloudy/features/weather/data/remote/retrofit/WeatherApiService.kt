package com.example.cloudy.features.weather.data.remote.retrofit

import com.example.cloudy.features.weather.data.remote.dto.WeatherDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("v1/forecast?hourly=temperature_2m,weathercode,relativehumidity_2m,windspeed_10m,pressure_msl")
    suspend fun getWeatherData(
        @Query("latitude") lat: Double,
        @Query("longitude") long: Double
    ): Response<WeatherDto>

    companion object {
        const val BASE_URL = "http://api.openweathermap.org/"
    }
}