package com.example.weatherapplication.data.model

//Data class represented in weather api response
data class SYS(
    val type: Int,
    val id: Double,
    val country: String,
    val sunrise: Double,
    val sunset: Double
)
