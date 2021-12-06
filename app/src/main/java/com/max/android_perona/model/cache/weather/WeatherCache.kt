package com.max.android_perona.model.cache.weather

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val APP_WEATHER_CACHE_TABLE_NAME = "app_weather_cache"

/**
 * 快取 - 天氣
 *
 * @property locationName 地區名稱
 * @property temperatureInfo 溫度詳細資料
 */
@Entity(tableName = APP_WEATHER_CACHE_TABLE_NAME)
data class WeatherCache(
    @PrimaryKey
    @ColumnInfo(name = "location_name")
    val locationName: String,
    @ColumnInfo(name = "temperature_info")
    val temperatureInfo: List<TemperatureInfoCache>,
)

/**
 * 溫度詳細資料
 *
 * @property startTime Long
 * @property endTime Long
 * @property temperature 溫度
 * @property unit 單位
 */
data class TemperatureInfoCache(
    val startTime: Long,
    val endTime: Long,
    val temperature: String,
    val unit: String
)