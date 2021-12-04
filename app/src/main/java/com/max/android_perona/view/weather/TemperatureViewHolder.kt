package com.max.android_perona.view.weather

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.max.android_perona.databinding.ItemWeatherTemperatureBinding

class TemperatureViewHolder(
    private val binding: ItemWeatherTemperatureBinding,
    private val onClickListener: TemperatureOnClickListener
) : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun onBind(item: WeatherItem.Temperature?) {
        item ?: return

        itemView.setOnClickListener {
            onClickListener.onClick(item)
        }

        binding.textViewStartTime.text = item.startTime
        binding.textViewEndTime.text = item.endTime
        binding.textViewTemperature.text = "${item.value}${item.unit}"
    }
}