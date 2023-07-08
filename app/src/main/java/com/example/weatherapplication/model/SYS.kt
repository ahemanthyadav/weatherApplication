package com.example.weatherapplication.model

//Data class represented in weather api response
data class SYS(
    val type: Int,
    val id: Double,
    val country: Float,
    val sunrise: Double,
    val sunset: Double
)
