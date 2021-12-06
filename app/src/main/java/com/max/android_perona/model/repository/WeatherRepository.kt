package com.max.android_perona.model.repository

interface WeatherRepository {
    suspend fun getData(locationName: String): RepositoryWeather?
}