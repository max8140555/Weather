package com.max.android_perona.model.repository

import com.max.android_perona.model.CalendarUtil.getTaiwanTime
import com.max.android_perona.model.WEATHER_DATE_FORMATTER
import com.max.android_perona.model.cache.CacheDatabase
import com.max.android_perona.model.cache.weather.TemperatureInfoCache
import com.max.android_perona.model.cache.weather.WeatherCache
import com.max.android_perona.model.service.weather.WeatherService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class WeatherRepositoryImpl(
    private val weatherService: WeatherService,
    private val cacheDatabase: CacheDatabase
) : WeatherRepository {

    override suspend fun getData(locationName: String): RepositoryWeather? =
        withContext(Dispatchers.IO) {
            val targetCache = cacheDatabase.weatherCacheDao()
                .queryFilterLocationName(locationName)
            val nowTaiwanTime = getTaiwanTime().timeInMillis
            val firstInfo = targetCache?.temperatureInfo?.firstOrNull()

            if (firstInfo != null) {
                val firstInfoStartTime = firstInfo.startTime
                val firstInfoEndTime = firstInfo.endTime

                if (nowTaiwanTime in firstInfoStartTime..firstInfoEndTime) {
                    val data = RepositoryWeather(
                        targetCache.locationName,
                        targetCache.temperatureInfo.map { infoCache ->
                            TemperatureInfo(
                                startTime = infoCache.startTime,
                                endTime = infoCache.endTime,
                                temperature = infoCache.temperature,
                                unit = infoCache.unit
                            )
                        }
                    )

                    return@withContext data
                }
            }

            return@withContext try {
                val locationNames = listOf(locationName)
                val result = weatherService.getWeather(
                    locationName = locationNames
                ).body()

                if (result != null && result.success == "true") {
                    val records = result.records
                    val location = records.locations.firstOrNull()
                    val weatherElement =
                        location?.weatherElements?.find { it.elementName == "MinT" }
                    val formatter = WEATHER_DATE_FORMATTER.clone() as SimpleDateFormat

                    val listTemperatureCacheInfo = weatherElement?.timeAndParameters?.map { info ->
                        TemperatureInfoCache(
                            startTime = formatter.parse(info.startTime).time,
                            endTime = formatter.parse(info.endTime).time,
                            temperature = info.parameter.parameterName,
                            unit = info.parameter.parameterUnit
                        )
                    }
                    if (location != null && weatherElement != null && listTemperatureCacheInfo != null) {
                        val cache = WeatherCache(
                            locationName = location.locationName,
                            temperatureInfo = listTemperatureCacheInfo
                        )

                        cacheDatabase.weatherCacheDao().insert(cache)

                        val listTemperatureInfo = cache.temperatureInfo.map { info ->
                            TemperatureInfo(
                                startTime = info.startTime,
                                endTime = info.endTime,
                                temperature = info.temperature,
                                unit = info.unit
                            )
                        }

                        RepositoryWeather(
                            cache.locationName,
                            listTemperatureInfo
                        )
                    } else {
                        null
                    }
                } else {
                    null
                }
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
}
