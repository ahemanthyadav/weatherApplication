package com.example.weatherapplication.data.repository.api;


import com.example.weatherapplication.Constants;
import com.example.weatherapplication.data.model.WeatherNetworkModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiClient {

    // api call to get weather from city
    //Note:: suspend and retrofit call does not work with each other
    @GET("/data/2.5/weather")
    Call<WeatherNetworkModel> getWeatherFromCity(@Query("q") String city, @Query(Constants.apiKey) String apiValue);

    // api call to get weather from city gps location
    @GET("/data/2.5/weather")
    Call<WeatherNetworkModel> getWeatherFromGeoLocation(@Query("lat") String latitude,@Query("lon") String longitude, @Query(Constants.apiKey) String apiValue);

}
