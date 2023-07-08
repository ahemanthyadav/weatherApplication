package com.example.weatherapplication.ui.viewmModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapplication.data.model.WeatherNetworkModel
import com.example.weatherapplication.data.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.logging.Logger
import javax.inject.Inject


@HiltViewModel
class WeatherSearchViewModel @Inject constructor(private val networkRepository: NetworkRepository) : ViewModel() {

    private val _weatherData: MutableLiveData<WeatherNetworkModel?> = MutableLiveData()
    val weatherData: LiveData<WeatherNetworkModel?> = _weatherData

    fun fetchWeatherFromCity(city: String){
        // Use viewModelScope to collect weather data from flow
        viewModelScope.launch {
            println("weather viewmodel: $city")
           networkRepository.getWeatherFromCity(city).collect{
               println(it)
               _weatherData.postValue(it)
               //reset the value in view model to  prevent navigation back to details screen
               /*
               it?.let {
                   //post value if collected weather data is not null
                   _weatherData.postValue(it)
               }

                */
           }
        }
    }

    fun verifyCity(city: String) = networkRepository.verifyCityName(city)



}