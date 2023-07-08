package com.example.weatherapplication.ui.viewmModels

import com.example.weatherapplication.data.model.WeatherNetworkModel
import com.example.weatherapplication.data.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherDetailsViewModel @Inject constructor(private val networkRepository: NetworkRepository) {


}