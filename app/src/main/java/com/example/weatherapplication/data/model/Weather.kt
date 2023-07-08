package com.example.weatherapplication.data.model

import com.squareup.moshi.Json

//Data class represented in weather api response
data class Weather(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "main") val main: String,
    @field:Json(name = "description") val description: String,
    @field:Json(name = "icon") val icon: String
)
