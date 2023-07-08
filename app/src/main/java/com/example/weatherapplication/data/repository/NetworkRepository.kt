package com.example.weatherapplication.data.repository

import android.util.Log
import com.example.weatherapplication.Constants
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

    //As business logic is small, for simplicity  using Repository as Data Layer as well not
    val TAG = "NetworkRepositoryResponse Log"
    private  var latestWeatherData: WeatherNetworkModel? = null

    //Repository call for getting weather data
    suspend fun getWeatherFromCity(city: String): Flow<WeatherNetworkModel?> {
        // Weather data is sent as flow to viewModel for UI Consumption
        return flow<WeatherNetworkModel?> {
            var weatherData: WeatherNetworkModel? = null
            //println("weather NetworkRepository: $city")
            val request = apiService.getWeatherFromCity(city = city)
            val response = request.execute()
           Log.d(TAG,response.toString())
                       when(response.code()){
                           200 -> {
                               //Api call is success
                               //updating weatherModel data with latest network data
                               weatherData = response.body()
                               weatherData?.let{
                                   /*save to global variable for Details screen consumption.
                                    latestWeatherData variable is reset after details screen consumed it
                                    */
                                   latestWeatherData = it
                                   generateImageURL(it)
                               }
                           }

                           401 ->{
                               //  Unauthorized Error
                               Log.v(TAG, response.code().toString())
                               Log.v(TAG,response.message())

                           }
                           404 ->{
                               //  Not Found Error
                               Log.v(TAG, response.code().toString())
                               Log.v(TAG,response.message())
                           }
                           429 ->{
                               // Too Many Requests
                               Log.v(TAG, response.code().toString())
                               Log.v(TAG,response.message())
                           }
                           in 500..599 ->{
                               // Unexpected Error
                               Log.v(TAG, response.code().toString())
                               Log.v(TAG,response.message())
                           }
                           else ->{
                               // Network Error
                               Log.v(TAG, response.code().toString())
                               Log.v(TAG,response.message())
                           }
                       }
            emit(weatherData)
        }.flowOn(Dispatchers.IO)
    }


    fun getStoredWeatherNetworkData(): WeatherNetworkModel?{
        //function to get last searched data in memory
        // check if latestResponseData is initialized
        val localWeatherData = latestWeatherData?.copy()
        //reset latestWeatherData Data to null so Transition  between search and details can happen
        latestWeatherData = null
        return localWeatherData
    }

    fun verifyCityName(city: String) = !checkIsStringEmpty(city) and checkIfAllCharacters(city)
    fun checkIsStringEmpty(city: String) = city.isEmpty()
    fun checkIfAllCharacters(city: String) = city.allLettersAndSpace()

    //Extension function for checking all letters and space
    fun String.allLettersAndSpace(): Boolean = this.matches(Regex("^[A-Za-z\\s]+$"))

    fun generateImageURL(weatherNetworkModel: WeatherNetworkModel) = "${Constants.imageURl}/img/wn/${weatherNetworkModel.weather[0].icon}@2x.png"



}