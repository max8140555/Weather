package com.max.android_perona.model.service.weather

import com.max.android_perona.model.service.ResponseWeather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

private const val AUTHORIZATION = "CWB-BD75E220-5A4D-408E-84BB-9EB913B5E17E"
private const val JSON = "JSON"

interface WeatherService {

    @GET("v1/rest/datastore/F-C0032-001")
    suspend fun getWeather(
        @Query("Authorization") authorization: String = AUTHORIZATION,
        @Query("limit ") limit: Int? = null,
        @Query("offset ") offset: Int? = null,
        @Query("format") format: String = JSON,
        @Query("locationName") locationName: List<String>,
        @Query("elementName") elementName: List<String> = emptyList(),
        @Query("sort") sort: String = "",
        @Query("startTime") startTime: List<String> = emptyList(),
        @Query("timeFrom") timeFrom: String = "",
        @Query("timeTo") timeTo: String = ""
    ): Response<ResponseWeather>
}