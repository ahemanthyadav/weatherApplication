package com.example.weatherapplication.data.model

import com.squareup.moshi.Json

//Data class represented in weather api response
data class SYS(
    @field:Json(name = "type") val type: Int,
    @field:Json(name = "id") val id: Double,
    @field:Json(name = "country") val country: String,
    @field:Json(name = "sunrise") val sunrise: Double,
    @field:Json(name = "sunset") val sunset: Double
)
