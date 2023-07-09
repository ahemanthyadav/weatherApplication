package com.example.weatherapplication.ui.viewmModels

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.weatherapplication.data.model.WeatherNetworkModel
import com.example.weatherapplication.data.repository.DataStoreRepository
import com.example.weatherapplication.data.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherDetailsViewModel @Inject constructor(private val networkRepository: NetworkRepository, private val dataStoreRepository: DataStoreRepository): ViewModel() {
    //val weatherImage: StateFlow<Drawable?> = networkRepository.weatherImage.asLiveData(context = context)
    val weatherImage: StateFlow<Drawable?> = networkRepository.weatherImage

    private val _repositoryWeatherData: MutableLiveData<WeatherNetworkModel> = MutableLiveData<WeatherNetworkModel>()
    val repositoryWeatherData: LiveData<WeatherNetworkModel> = _repositoryWeatherData
    fun getWeatherDataFromNetworkRepository(){
        //check if weather Data exist in repository if data exist and not null send it to details fragments using live data
        networkRepository.getStoredWeatherNetworkData()?.let{
            //send weather Data to details fragments using live data
            _repositoryWeatherData.postValue(it)
        }
    }

    fun saveLatestCityName(cityName: String) = dataStoreRepository.apply {
        CoroutineScope(Dispatchers.IO).launch {
            saveLatestCityName(cityName)
        }
    }


    /*
    //No longer needed
    //This catching method is not meeting expected dynamic loading, need more work to improve speed
    //State flow seems a better alternative

    val _imageData: MutableLiveData<Drawable> = MutableLiveData()
    val imageData: LiveData<Drawable?> = _imageData
    fun fetchWeatherImageFromRepository(){
        // Use viewModelScope to collect weather data from flow
        viewModelScope.launch {
            networkRepository.sendDrawableAsFlow().collect(){ image ->
                _imageData.postValue(image)
            }

        }
    }

    // Use viewModelScope to collect weather data from flow
    fun getWeatherImageFromRepository() = run { _imageData.postValue(networkRepository.getStoredImage()) }
     */



}