package com.max.android_perona.model.repository

/**
 * 天氣
 *
 * @property locationName 地區名稱
 * @property temperatureInfo 溫度詳細資料
 */
data class RepositoryWeather(
    val locationName: String,
    val temperatureInfo: List<TemperatureInfo>,
)

/**
 * 溫度詳細資料
 *
 * @property startTime Long
 * @property endTime Long
 * @property temperature 溫度
 * @property unit 單位
 */
data class TemperatureInfo(
    val startTime: Long,
    val endTime: Long,
    val temperature: String,
    val unit: String
)