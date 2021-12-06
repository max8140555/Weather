package com.max.android_perona.model

import java.text.SimpleDateFormat
import java.util.*

val WEATHER_DATE_FORMATTER by lazy {
    createDateFormat("yyyy-MM-dd HH:mm:ss")
}

private val GMT_PLUS_EIGHT_TIME_ZONE = TimeZone.getTimeZone("GMT+8")

private fun createDateFormat(pattern: String): SimpleDateFormat {
    return SimpleDateFormat(pattern, Locale.TAIWAN).apply {
        timeZone = GMT_PLUS_EIGHT_TIME_ZONE
    }
}
