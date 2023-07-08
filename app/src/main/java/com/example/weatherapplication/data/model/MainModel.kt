package com.example.weatherapplication.data.model

//Data class represented in weather api response
data class MainModel(
    val temp: Float,
    val feels_like: Float,
    val temp_min: Float,
    val temp_max: Float,
    val pressure: Int,
    val humidity: Int
)
