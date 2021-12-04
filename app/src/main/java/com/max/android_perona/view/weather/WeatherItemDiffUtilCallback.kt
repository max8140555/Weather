package com.max.android_perona.view.weather

import androidx.recyclerview.widget.DiffUtil

class WeatherItemDiffUtilCallback : DiffUtil.ItemCallback<WeatherItem>() {

    override fun areItemsTheSame(
        oldItem: WeatherItem,
        newItem: WeatherItem
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: WeatherItem,
        newItem: WeatherItem
    ): Boolean {
        return oldItem == newItem
    }
}