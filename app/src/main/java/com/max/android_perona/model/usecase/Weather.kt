package com.max.android_perona.model.usecase

/**
 * 天氣
 *
 * @property locationName 地區名稱
 * @property temperatureInfo 溫度詳細資料
 */
data class Weather(
    val locationName: String,
    val temperatureInfo: List<TemperatureInfo>,
)

/**
 * 溫度詳細資料
 *
 * @property startTime String
 * @property endTime String
 * @property temperature 溫度
 * @property unit 單位
 */
data class TemperatureInfo(
    val startTime: String,
    val endTime: String,
    val temperature: String,
    val unit: String
)