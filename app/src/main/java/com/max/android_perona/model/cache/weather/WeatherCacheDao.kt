package com.max.android_perona.model.cache.weather

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WeatherCacheDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg equityCache: WeatherCache)

    @Query(value = "SELECT * FROM $APP_WEATHER_CACHE_TABLE_NAME WHERE location_name IN (:locationName)")
    suspend fun queryFilterLocationName(locationName: String): WeatherCache?
}