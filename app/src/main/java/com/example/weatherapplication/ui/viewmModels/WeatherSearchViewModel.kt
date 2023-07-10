package com.example.weatherapplication.ui.viewmModels

import android.os.health.ServiceHealthStats
import android.provider.SyncStateContract.Constants
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapplication.data.model.WeatherNetworkModel
import com.example.weatherapplication.data.repository.DataStoreRepository
import com.example.weatherapplication.data.repository.LocationRepository
import com.example.weatherapplication.data.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
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

    private val compositeDisposable = CompositeDisposable()

    fun fetchWeatherFromCity(city: String){
        // Use viewModelScope to collect weather data from flow
        viewModelScope.launch {
            println("weather viewmodel: $city")
            //making network call and collecting result
            // passing back to
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
/*
    fun getCurrentLocation() {
        //locationRepository.getLastLocation()
        locationRepository.getLatestLocation()
        //locationRepository.getCurrentLocationAsFlow(false)
        viewModelScope.launch{
           // locationRepository.getCurrentLocation1()
        }
        //locationRepository.getCurrentLocationAsFlow(false)
    }

 */

    fun getCurrentLocation() {
        compositeDisposable.add(
            locationRepository.latestLocationObservable
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe { location ->
                    println("location:: $location")
                   CoroutineScope(Dispatchers.IO).launch {
                       // call network api to fetch weather data using lat and long and collecting data
                       networkRepository.getWeatherFromCityLatLong(location.latitude.toString(),location.longitude.toString()).collect{
                           println(it)
                           _weatherData.postValue(it)
                       }
                   }

                }
        )

    }


}