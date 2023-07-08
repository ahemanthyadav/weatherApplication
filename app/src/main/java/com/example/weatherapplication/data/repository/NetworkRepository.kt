package com.example.weatherapplication.data.repository

import android.util.Log
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

    val tag = "NetworkRepositoryResponse Log"
    private lateinit var latestResponseData: WeatherNetworkModel
    //Repository call for getting weather data
    suspend fun getWeatherFromCity(city: String): Flow<WeatherNetworkModel?> {
        // Weather data is sent as flow to viewModel for UI Consumption
        return flow<WeatherNetworkModel?> {
            var weatherModel: WeatherNetworkModel? = null
            //println("weather NetworkRepository: $city")
            val request = apiService.getWeatherFromCity(city = city)
            val response = request.execute()
           Log.d(tag,response.toString())
                       when(response.code()){
                           200 -> {
                               //Api call is success
                               //updating weatherModel data with latest network data
                               weatherModel = response.body()
                               weatherModel?.let{
                                   // save
                                   latestResponseData = it
                               }
                           }

                           401 ->{
                               //  Unauthorized Error
                               Log.v(tag, response.code().toString())
                               Log.v(tag,response.message())

                           }
                           404 ->{
                               //  Not Found Error
                               Log.v(tag, response.code().toString())
                               Log.v(tag,response.message())
                           }
                           429 ->{
                               // Too Many Requests
                               Log.v(tag, response.code().toString())
                               Log.v(tag,response.message())
                           }
                           in 500..599 ->{
                               // Unexpected Error
                               Log.v(tag, response.code().toString())
                               Log.v(tag,response.message())
                           }
                           else ->{
                               // Network Error
                               Log.v(tag, response.code().toString())
                               Log.v(tag,response.message())
                           }
                       }
            emit(weatherModel)
        }.flowOn(Dispatchers.IO)
    }


    fun getStoredWeatherNetworkData(): WeatherNetworkModel?{
        //function to get last searched data in memory
        // check if latestResponseData is initialized
        return if(::latestResponseData.isInitialized) {
            // if initialized return data
            latestResponseData
        }else{
            //else send null
            null
        }
    }

}