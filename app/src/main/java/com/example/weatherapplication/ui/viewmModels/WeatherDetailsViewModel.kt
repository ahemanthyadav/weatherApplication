package com.example.weatherapplication.ui.viewmModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapplication.data.model.WeatherNetworkModel
import com.example.weatherapplication.data.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherDetailsViewModel @Inject constructor(private val networkRepository: NetworkRepository): ViewModel() {

    private val _repositoryWeatherData: MutableLiveData<WeatherNetworkModel> = MutableLiveData<WeatherNetworkModel>()
    val repositoryWeatherData: LiveData<WeatherNetworkModel> = _repositoryWeatherData
    fun getWeatherDataFromNetworkRepository(){
        //check if weather Data exist in repository if data exist and not null send it to details fragments using live data
        networkRepository.getStoredWeatherNetworkData()?.let{
            //send weather Data to details fragments using live data
            _repositoryWeatherData.postValue(it)
        }
    }


}