package com.lyvetech.cloudy.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lyvetech.cloudy.data.local.entity.WeatherEntity
import com.lyvetech.cloudy.data.local.entity.WeatherForecastEntity

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weatherEntity: WeatherEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherForecast(weatherForecastEntity: WeatherForecastEntity)

    @Query("DELETE FROM weather_table")
    suspend fun deleteAllWeather()

    @Query("DELETE FROM weather_forecast_table")
    suspend fun deleteAllWeatherForecast()

    @Query("SELECT * FROM weather_table")
    suspend fun getWeather(): WeatherEntity?

    @Query("SELECT * FROM weather_forecast_table")
    suspend fun getWeatherForecast(): List<WeatherForecastEntity?>
}