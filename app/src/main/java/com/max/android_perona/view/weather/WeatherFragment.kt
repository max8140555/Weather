package com.max.android_perona.view.weather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.max.android_perona.R
import com.max.android_perona.databinding.FragmentWeatherBinding
import com.max.android_perona.view.detail.DetailTemperature
import com.max.android_perona.view.detail.DetailTemperatureActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherFragment : Fragment(R.layout.fragment_weather) {

    companion object {
        fun newInstance() = WeatherFragment()
    }

    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!

    private val viewModel: WeatherViewModel by viewModel()

    private val temperatureOnClickListener by lazy {
        object : TemperatureOnClickListener {
            override fun onClick(item: WeatherItem.Temperature) {

                val data = DetailTemperature(
                    startTime = item.startTime,
                    endTime = item.endTime,
                    value = item.value,
                    unit = item.unit,
                )

                val intent = DetailTemperatureActivity.createIntent(requireContext(), data)
                startActivity(intent)
            }
        }
    }

    private val weatherAdapter by lazy {
        WeatherAdapter(temperatureOnClickListener)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentWeatherBinding.bind(view)

        initRecyclerView()

        val mockData = listOf(
            WeatherItem.Temperature(
                "2021-12-05 06:00:00",
                "2021-12-05 18:00:00",
                "27",
                "C"
            ),
            WeatherItem.Picture(
                R.drawable.ic_launcher_background
            ),
            WeatherItem.Temperature(
                "2021-12-05 18:00:00",
                "2021-12-06 06:00:00",
                "28",
                "C"
            ),
            WeatherItem.Picture(
                R.drawable.ic_launcher_background
            )
        )
        weatherAdapter.submitList(mockData)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun initRecyclerView() {
        binding.recyclerViewWeather.adapter = weatherAdapter
    }
}