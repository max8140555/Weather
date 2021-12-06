package com.max.android_perona.model.usecase

import com.max.android_perona.model.WEATHER_DATE_FORMATTER
import com.max.android_perona.model.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat

class WeatherUseCaseImpl(private val weatherRepository: WeatherRepository) : WeatherUseCase {

    override suspend fun getData(locationName: String): Weather? =
        withContext(Dispatchers.Default) {
            val data = weatherRepository.getData(locationName)
            val formatterT = WEATHER_DATE_FORMATTER.clone() as SimpleDateFormat

            return@withContext if (data != null) {
                Weather(
                    data.locationName,
                    data.temperatureInfo.map {
                        TemperatureInfo(
                            startTime = formatterT.format(it.startTime),
                            endTime = formatterT.format(it.endTime),
                            temperature = it.temperature,
                            unit = it.unit
                        )
                    }
                )
            } else {
                null
            }
        }
}