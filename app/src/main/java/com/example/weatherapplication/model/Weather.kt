package com.example.weatherapplication.model

//Data class represented in weather api response
data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)
