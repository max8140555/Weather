package com.max.android_perona.model.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.max.android_perona.model.cache.converter.ListTemperatureInfoConverter
import com.max.android_perona.model.cache.weather.WeatherCache
import com.max.android_perona.model.cache.weather.WeatherCacheDao

@Database(
    entities = [
        WeatherCache::class,
    ],
    version = 1
)
@TypeConverters(ListTemperatureInfoConverter::class)
abstract class CacheDatabase : RoomDatabase() {
    abstract fun weatherCacheDao(): WeatherCacheDao
}