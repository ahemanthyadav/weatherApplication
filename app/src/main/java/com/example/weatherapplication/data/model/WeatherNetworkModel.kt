package com.example.weatherapplication.data.model

import com.squareup.moshi.Json

//Data from weather api response
data class WeatherNetworkModel(
    @Json(name = "coord")
    val coord: Coordinate,
    @Json(name = "weather")
    val weather: List<Weather>,
    @Json(name = "base")
    val base: String,
    @Json(name = "main")
    val main: MainModel,
    @Json(name = "visibility")
    val visibility: Int,
    @Json(name = "wind")
    val wind: Wind,
    @Json(name = "clouds")
    val clouds: Clouds,
    @Json(name = "dt")
    val dt: Double,
    @Json(name = "sys")
    val sys: SYS,
    @Json(name = "timezone")
    val timezone: Int,
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "cod")
    val cod: Int
)
