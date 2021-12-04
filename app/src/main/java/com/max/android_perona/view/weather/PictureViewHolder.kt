package com.max.android_perona.view.weather

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.max.android_perona.databinding.ItemWeatherPictureBinding

class PictureViewHolder(
    private val binding: ItemWeatherPictureBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: WeatherItem.Picture?) {
        item ?: return

        val drawable = ContextCompat.getDrawable(itemView.context, item.drawableRes)
        binding.imageViewPicture.setImageDrawable(drawable)
    }
}