package com.max.android_perona.view.detail

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * 溫度
 *
 * @param startTime 開始時間
 * @param endTime 結束時間
 * @param value 數值
 * @param unit 單位
 */
@Parcelize
data class DetailTemperature(
    val startTime: String,
    val endTime: String,
    val value: String,
    val unit: String
) : Parcelable
