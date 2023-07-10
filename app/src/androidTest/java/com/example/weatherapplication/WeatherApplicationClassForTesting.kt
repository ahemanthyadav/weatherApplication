package com.example.weatherapplication

import dagger.hilt.android.testing.CustomTestApplication

@CustomTestApplication(BaseApplication::class)
interface WeatherApplicationClassForTesting {
}