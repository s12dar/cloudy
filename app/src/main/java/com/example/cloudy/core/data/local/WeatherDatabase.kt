package com.example.cloudy.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cloudy.core.data.local.WeatherDatabase.Companion.DB_VERSION
import com.example.cloudy.features.weather.data.local.dao.WeatherDao
import com.example.cloudy.features.weather.data.local.entity.WeatherEntity
import com.example.cloudy.features.weather.data.util.Converters


@Database(
    entities = [WeatherEntity::class],
    version = DB_VERSION,
    exportSchema = false
)
@TypeConverters(
    Converters::class
)
abstract class WeatherDatabase : RoomDatabase() {

    abstract val weatherDao: WeatherDao

    companion object {
        const val DB_NAME = "weather.db"
        const val DB_VERSION = 1
    }
}