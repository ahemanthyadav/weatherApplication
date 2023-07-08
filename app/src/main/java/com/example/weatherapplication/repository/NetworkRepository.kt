package com.example.weatherapplication.repository

import com.example.weatherapplication.model.WeatherNetworkModel
import com.example.weatherapplication.repository.api.ApiService
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkRepository @Inject constructor(private val apiService: ApiService) {

    //
    suspend fun getWeatherFromCity(city: String): Flow<WeatherNetworkModel> {
        return flow<WeatherNetworkModel> {
            emit( apiService.getWeatherFromCity(city = city) )
        }.flowOn(Dispatchers.IO)
    }

}