package com.example.weatherapplication.repository.api

import com.example.weatherapplication.Constants
import com.example.weatherapplication.model.WeatherNetworkModel
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {

    @GET("/geo/1.0/direct")
    // api call to get weather from city
    suspend fun getWeatherFromCity(@Query("q") city: String,@Query(Constants.apiKey) apiValue: String = Constants.apiValue): WeatherNetworkModel

    @GET("/data/2.5/weather")
    suspend fun getWeatherFromGeoLocation(){

    }

    @GET("")
    suspend fun getGeoLocationUsingCity(){

    }

    //This function is not needed as we will be using coil library
    @GET
    suspend fun getWeatherImage(@Url url: String){

    }
}