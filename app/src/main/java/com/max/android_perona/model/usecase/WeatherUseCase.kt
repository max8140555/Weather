package com.max.android_perona.model.usecase

interface WeatherUseCase {
    suspend fun getData(locationName: String): Weather?
}