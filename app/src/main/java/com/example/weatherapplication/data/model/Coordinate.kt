package com.example.weatherapplication.data.model

import com.squareup.moshi.Json

//Data class represented in weather api response
data class Coordinate(
    @field:Json(name = "lon") val lon: Float,
    @field:Json(name = "lat") val lat: Float
)
