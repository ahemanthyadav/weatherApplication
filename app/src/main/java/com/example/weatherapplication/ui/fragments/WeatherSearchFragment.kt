package com.example.weatherapplication.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.enableSavedStateHandles
import androidx.navigation.fragment.findNavController
import com.example.weatherapplication.R
import com.example.weatherapplication.databinding.WeatherSearchBinding
import com.example.weatherapplication.ui.viewmModels.WeatherSearchViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class WeatherSearchFragment : Fragment() {

    private var _binding: WeatherSearchBinding? = null
    //view model
    private val weatherSearchViewModel: WeatherSearchViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    var cityNameSize: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = WeatherSearchBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.city.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                cityNameSize = p0?.length ?: 0
                val cityNameSize1 = p0?.length ?: 0
                if(cityNameSize1 > 0){
                    makeSearchButtonClickable()
                }else{
                    makeSearchButtonNonClickable()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        binding.searchButton.setOnClickListener {
            makeSearchButtonNonClickable()
            val cityText = binding.city.text.toString()
            fetchCityWeather(cityText)
            binding.city.text.clear()

            //findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    fun makeSearchButtonClickable(){
        binding.searchButton.alpha = 1F
        binding.searchButton.isClickable = true

    }

    fun makeSearchButtonNonClickable(){
        binding.searchButton.alpha = 0.5F
        binding.searchButton.isClickable = false

    }

    fun fetchCityWeather(city: String){
        weatherSearchViewModel.fetchWeatherFromCity(city)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}