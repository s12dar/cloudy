package com.lyvetech.cloudy.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lyvetech.cloudy.data.local.db.WeatherDatabase.Companion.DB_VERSION
import com.lyvetech.cloudy.data.local.db.WeatherDatabase.Companion.EXP_FALSE
import com.lyvetech.cloudy.data.local.dao.WeatherDao
import com.lyvetech.cloudy.data.local.entity.WeatherEntity
import com.lyvetech.cloudy.data.util.Converters

@Database(
    entities = [WeatherEntity::class],

    version = DB_VERSION,
    exportSchema = EXP_FALSE
)
@TypeConverters(
    Converters::class
)
abstract class WeatherDatabase : RoomDatabase() {
    abstract val weatherDao: WeatherDao

    companion object {
        const val DB_NAME = "weather.db"
        const val DB_VERSION = 5
        const val EXP_FALSE = false
    }
}