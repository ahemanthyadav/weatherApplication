package com.example.weatherapplication.data.repository

import com.example.weatherapplication.data.model.WeatherNetworkModel
import com.example.weatherapplication.data.repository.api.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkRepository @Inject constructor(private val apiService: ApiService) {

    //Repository call for getting weather data
    suspend fun getWeatherFromCity(city: String): Flow<WeatherNetworkModel> {
        // Weather data is sent as flow to viewModel
        return flow<WeatherNetworkModel> {
            println("weather NetworkRepository: $city")
            emit( apiService.getWeatherFromCity(city = city) )
        }.flowOn(Dispatchers.IO)
    }

}