package com.example.weatherapplication.ui.viewmModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LocationViewModel: ViewModel() {

    private val _locationCalledOnce: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val locationCalledOnce: LiveData<Boolean> = _locationCalledOnce

    fun madeInitialLocationSearch(locationCalledOnce: Boolean) {
        // Save first click through out the application
        _locationCalledOnce.value = locationCalledOnce
    }


}