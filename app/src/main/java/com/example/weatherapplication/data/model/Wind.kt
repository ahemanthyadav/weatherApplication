package com.example.weatherapplication.data.model

import com.squareup.moshi.Json

//Data class represented in weather api response
data class Wind(
    @field:Json(name = "speed") val speed: Float,
    @field:Json(name = "deg") val deg: Int,
    @field:Json(name = "gust") val gust: Float
)
