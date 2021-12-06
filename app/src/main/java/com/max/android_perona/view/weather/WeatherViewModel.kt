package com.max.android_perona.view.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.max.android_perona.R
import com.max.android_perona.model.service.util.LoadApiStatus
import com.max.android_perona.model.usecase.WeatherUseCase
import kotlinx.coroutines.launch

class WeatherViewModel(private val weatherUseCase: WeatherUseCase) : ViewModel() {

    private val _loadApiStatus = MutableLiveData<LoadApiStatus>()
    val loadApiStatus: LiveData<LoadApiStatus> get() = _loadApiStatus

    private val _items = MutableLiveData<List<WeatherItem>>()
    val items: LiveData<List<WeatherItem>> get() = _items

    init {
        getLocationWeather("臺北市")
    }

    private fun getLocationWeather(locationName: String) {
        viewModelScope.launch {

            _loadApiStatus.value = LoadApiStatus.LOADING

            val result = weatherUseCase.getData(locationName)

            if (result == null) {
                _loadApiStatus.value = LoadApiStatus.ERROR
                _items.value = emptyList()
            } else {
                val items = mutableListOf<WeatherItem>()

                result.temperatureInfo.forEach { info ->
                    val temperatureItem = WeatherItem.Temperature(
                        info.startTime, info.endTime, info.temperature, info.unit
                    )
                    val pictureItem = WeatherItem.Picture(
                        R.drawable.ic_launcher_background
                    )
                    items.add(temperatureItem)
                    items.add(pictureItem)
                }

                _loadApiStatus.value = LoadApiStatus.DONE
                _items.value = items
            }
        }
    }
}