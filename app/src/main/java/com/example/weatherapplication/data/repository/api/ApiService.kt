package com.example.weatherapplication.data.repository.api

import com.example.weatherapplication.Constants
import com.example.weatherapplication.data.model.WeatherNetworkModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {


    // api call to get weather from city
    //Note:: suspend and retrofit call does not work with each other
    @GET("/data/2.5/weather")
    fun getWeatherFromCity(@Query("q") city: String,@Query(Constants.apiKey) apiValue: String = Constants.apiValue): Call<WeatherNetworkModel>

    @GET("/data/2.5/weather")
    fun getWeatherFromGeoLocation(){

    }

    @GET("/geo/1.0/direct")
    fun getGeoLocationUsingCity(){

    }

    //This function is not needed as we will be using coil library
    @GET
    fun getWeatherImage(@Url url: String){

    }
}