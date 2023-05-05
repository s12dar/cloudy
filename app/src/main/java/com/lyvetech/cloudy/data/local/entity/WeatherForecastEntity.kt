package com.lyvetech.cloudy.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lyvetech.cloudy.common.model.WeatherCondition
import com.lyvetech.cloudy.common.model.WeatherDescription
import com.lyvetech.cloudy.common.model.Wind

@Entity(tableName = "weather_forecast_table")
data class WeatherForecastEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val date: String,

    @Embedded
    val wind: Wind,

    @ColumnInfo(name = "weather_description")
    val networkWeatherDescriptions: List<WeatherDescription>,

    @Embedded
    val networkWeatherCondition: WeatherCondition
)