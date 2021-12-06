package com.max.android_perona.view.weather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import com.max.android_perona.R
import com.max.android_perona.databinding.FragmentWeatherBinding
import com.max.android_perona.model.service.util.LoadApiStatus
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
        observeViewModel()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun initRecyclerView() {
        binding.recyclerViewWeather.adapter = weatherAdapter
    }

    private fun observeViewModel() {
        viewModel.items.observe(viewLifecycleOwner, {
            it?.let { items ->
                weatherAdapter.submitList(items)
            }
        })
        viewModel.loadApiStatus.observe(viewLifecycleOwner, {
            it?.let { status ->
                when (status) {
                    LoadApiStatus.LOADING -> {
                        binding.progressBar.isVisible = true
                    }
                    LoadApiStatus.DONE -> {
                        binding.progressBar.isVisible = false
                    }
                    LoadApiStatus.ERROR -> {
                        binding.progressBar.isVisible = false
                        Toast.makeText(requireContext(), getString(R.string.api_error), Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }
}