package com.example.weatherapplication.data.model

import com.squareup.moshi.Json

//Data class represented in weather api response
data class Clouds (
    @field:Json(name = "all") val all: String
    )