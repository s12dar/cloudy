package com.lyvetech.cloudy.data.remote.retrofit

import com.lyvetech.cloudy.data.remote.dto.WeatherDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("/data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double
    ): Response<WeatherDto>

    @GET("data/2.5/forecast")
    suspend fun getWeatherForecast(
        @Query("id") cityId: Int
    ): Response<WeatherDto>

    companion object {
        const val BASE_URL = "http://api.openweathermap.org/"
    }
}