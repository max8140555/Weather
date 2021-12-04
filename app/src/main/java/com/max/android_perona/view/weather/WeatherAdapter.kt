package com.max.android_perona.view.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.max.android_perona.databinding.ItemWeatherPictureBinding
import com.max.android_perona.databinding.ItemWeatherTemperatureBinding
import java.lang.ClassCastException

class WeatherAdapter(
    private val temperatureOnClickListener: TemperatureOnClickListener,
    itemDiffCallback: DiffUtil.ItemCallback<WeatherItem> = WeatherItemDiffUtilCallback()
) : ListAdapter<WeatherItem, RecyclerView.ViewHolder>(itemDiffCallback) {

    companion object {
        private const val TEMPERATURE = 0
        private const val PICTURE = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is WeatherItem.Temperature -> TEMPERATURE
            is WeatherItem.Picture -> PICTURE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TEMPERATURE -> TemperatureViewHolder(
                ItemWeatherTemperatureBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                temperatureOnClickListener
            )
            PICTURE -> PictureViewHolder(
                ItemWeatherPictureBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> throw ClassCastException("Unknown ViewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TemperatureViewHolder -> holder.onBind(getItem(position) as? WeatherItem.Temperature)
            is PictureViewHolder -> holder.onBind(getItem(position) as? WeatherItem.Picture)
        }
    }

}