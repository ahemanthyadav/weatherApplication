package com.example.weatherapplication.ui.viewmModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapplication.data.model.WeatherNetworkModel
import com.example.weatherapplication.data.repository.DataStoreRepository
import com.example.weatherapplication.data.repository.LocationRepository
import com.example.weatherapplication.data.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WeatherSearchViewModel @Inject constructor(private val networkRepository: NetworkRepository,
                                                 private val dataStoreRepository: DataStoreRepository,
                                                 private val locationRepository: LocationRepository
) : ViewModel() {

    private val _weatherData: MutableLiveData<WeatherNetworkModel?> = MutableLiveData()
    val weatherData: LiveData<WeatherNetworkModel?> = _weatherData

    fun fetchWeatherFromCity(city: String){
        // Use viewModelScope to collect weather data from flow
        viewModelScope.launch {
            println("weather viewmodel: $city")
           networkRepository.getWeatherFromCity(city).collect{
               println(it)
               _weatherData.postValue(it)
           }
        }
    }

    fun verifyCity(city: String) = networkRepository.verifyCityName(city)

    fun getLatestCitySearched() = dataStoreRepository.getLatestCityNameFlow
    fun saveLatestCityName(cityName: String) = dataStoreRepository.apply {
        CoroutineScope(Dispatchers.IO).launch {
            saveLatestCityName(cityName)
        }
    }

    fun getCurrentLocation() {
        //locationRepository.getLastLocation()
        locationRepository.getLatestLocation()
        //locationRepository.getCurrentLocationAsFlow(false)
        viewModelScope.launch{
           // locationRepository.getCurrentLocation1()
        }
        //locationRepository.getCurrentLocationAsFlow(false)
    }
    fun getLastKnownLocation() {
        //locationRepository.getLastKnowLocationAsFlow(false)
        //locationRepository.getCurrentLocationAsFlow(false)
    }


}