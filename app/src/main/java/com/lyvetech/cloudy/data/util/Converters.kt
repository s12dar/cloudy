package com.lyvetech.cloudy.data.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lyvetech.cloudy.common.model.WeatherDescription
import java.lang.reflect.Type

class Converters {
    private val gson = Gson()

    private val type: Type = object : TypeToken<List<WeatherDescription?>?>() {}.type

    @TypeConverter
    fun fromWeatherDtoList(list: List<WeatherDescription?>?): String {
        return gson.toJson(list, type)
    }

    @TypeConverter
    fun toWeatherDtoList(json: String?): List<WeatherDescription> {
        return gson.fromJson(json, type)
    }
}