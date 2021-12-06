package com.max.android_perona.model.cache.converter

import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.max.android_perona.model.JsonParser
import com.max.android_perona.model.cache.weather.TemperatureInfoCache
import org.koin.java.KoinJavaComponent.getKoin

class ListTemperatureInfoConverter {

    private val jsonParser by lazy {
        getKoin().get<JsonParser>()
    }

    @TypeConverter
    fun fromStringToListTemperatureInfo(data: List<TemperatureInfoCache>): String {
        return jsonParser.toJson(data)
    }

    @TypeConverter
    fun formListTemperatureInfoToString(json: String): List<TemperatureInfoCache>? {
        return try {
            jsonParser.fromJson(json, object : TypeToken<List<TemperatureInfoCache>?>() {}.type)
        } catch (e: Throwable) {
            e.printStackTrace()
            null
        }
    }
}