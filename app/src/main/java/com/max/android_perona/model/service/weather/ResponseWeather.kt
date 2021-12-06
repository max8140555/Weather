package com.max.android_perona.model.service

import com.google.gson.annotations.SerializedName

data class ResponseWeather(
    @SerializedName("success")
    val success: String,
    @SerializedName("records")
    val records: Records,
)

data class Records(
    @SerializedName("datasetDescription")
    val datasetDescription: String,
    @SerializedName("location")
    val locations: List<Location>
)

data class Location(
    @SerializedName("locationName")
    val locationName: String,
    @SerializedName("weatherElement")
    val weatherElements: List<WeatherElement>
)

data class WeatherElement(
    @SerializedName("elementName")
    val elementName: String,
    @SerializedName("time")
    val timeAndParameters: List<TimeAndParameter>
)

data class TimeAndParameter(
    @SerializedName("startTime")
    val startTime: String,
    @SerializedName("endTime")
    val endTime: String,
    @SerializedName("parameter")
    val parameter: Parameter
)

data class Parameter(
    @SerializedName("parameterName")
    val parameterName: String,
    @SerializedName("parameterUnit")
    val parameterUnit: String
)
