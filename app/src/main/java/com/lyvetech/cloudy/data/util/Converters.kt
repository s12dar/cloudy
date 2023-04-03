package com.lyvetech.cloudy.data.util

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class Converters {

    private val moshi = Moshi.Builder().build()

    private val typeString = Types.newParameterizedType(
        List::class.java,
        String::class.javaObjectType
    )

    private val typeDouble = Types.newParameterizedType(
        List::class.java,
        Double::class.javaObjectType
    )

    private val typeInt = Types.newParameterizedType(
        List::class.java,
        Int::class.javaObjectType
    )

    private val adapterString: JsonAdapter<List<String>> = moshi.adapter(typeString)
    private val adapterDouble: JsonAdapter<List<Double>> = moshi.adapter(typeDouble)
    private val adapterInt: JsonAdapter<List<Int>> = moshi.adapter(typeInt)

    @TypeConverter
    fun fromString(data: String): List<String>? = adapterString.fromJson(data)

    @TypeConverter
    fun toString(data: List<String>?): String = adapterString.toJson(data)

    @TypeConverter
    fun toDouble(data: String): List<Double>? = adapterDouble.fromJson(data)

    @TypeConverter
    fun fromDouble(data: List<Double>?): String = adapterDouble.toJson(data)

    @TypeConverter
    fun fromInt(data: String): List<Int>? = adapterInt.fromJson(data)

    @TypeConverter
    fun toInt(data: List<Int>?): String = adapterInt.toJson(data)
}