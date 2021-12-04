package com.max.android_perona.view.weather

sealed class WeatherItem {

    /**
     * 溫度
     *
     * @param startTime 開始時間
     * @param endTime 結束時間
     * @param value 數值
     * @param unit 單位
     */

    data class Temperature(
        val startTime:String,
        val endTime:String,
        val value:String,
        val unit:String
    ): WeatherItem()

    /**
     * 照片
     *
     * @param drawableRes 圖片Res
     */
    data class Picture(
        val drawableRes: Int
    ): WeatherItem()
}
