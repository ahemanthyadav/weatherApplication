package com.example.weatherapplication.ui.fragments

import android.app.LocaleConfig
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import coil.request.ImageRequest
import com.example.weatherapplication.R
import com.example.weatherapplication.databinding.WeatherDetailsBinding
import com.example.weatherapplication.ui.viewmModels.WeatherDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class WeatherDetailsFragment : Fragment() {

    private var _binding: WeatherDetailsBinding? = null
    private val weatherDetailsViewModel: WeatherDetailsViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = WeatherDetailsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weatherDetailsViewModel.repositoryWeatherData.observe(viewLifecycleOwner){
            //setting the name of  city
            // print(it)
            binding.cityName.text = it.name
            //setting the text of city using just the first element of weather
            binding.cityWeather.text = it.weather[0].description
        }
        //check if weather Data exist in repository using viewModel
        weatherDetailsViewModel.getWeatherDataFromNetworkRepository()
        binding.searchAgainButton.setOnClickListener{
            //clear City Name In DataStore
            clearCityNameInDataStore()
            findNavController().navigate(R.id.details_search)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            weatherDetailsViewModel.weatherImage.collect(){imageDrawable ->
                // load image from cache to image view
                imageDrawable.let {
                    binding.weatherIcon.setImageDrawable(it)
                }
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun clearCityNameInDataStore() = weatherDetailsViewModel.saveLatestCityName("")
}