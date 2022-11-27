package com.lyvetech.cloudy.features.home.data.remote.retrofit

import com.lyvetech.cloudy.features.home.data.remote.dto.WeatherDto
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
        const val BASE_URL = "https://api.open-meteo.com/"
    }
}