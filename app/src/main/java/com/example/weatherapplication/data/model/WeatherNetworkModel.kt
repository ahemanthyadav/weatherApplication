package com.example.weatherapplication.data.model

import com.squareup.moshi.Json

//Data from weather api response
data class WeatherNetworkModel(
    @field:Json(name = "coord")
    val coord: Coordinate,
    @field:Json(name = "weather")
    val weather: List<Weather>,
    @field:Json(name = "base")
    val base: String,
    @field:Json(name = "main")
    val main: MainModel,
    @field:Json(name = "visibility")
    val visibility: Int,
    @field:Json(name = "wind")
    val wind: Wind,
    @field:Json(name = "clouds")
    val clouds: Clouds,
    @field:Json(name = "dt")
    val dt: Double,
    @field:Json(name = "sys")
    val sys: SYS,
    @field:Json(name = "timezone")
    val timezone: Int,
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "cod")
    val cod: Int
)
