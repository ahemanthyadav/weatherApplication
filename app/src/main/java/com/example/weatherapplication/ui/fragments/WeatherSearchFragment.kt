package com.example.weatherapplication.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.weatherapplication.R
import com.example.weatherapplication.databinding.WeatherSearchBinding
import com.example.weatherapplication.ui.viewmModels.WeatherSearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Duration

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class WeatherSearchFragment : Fragment() {

    private var _binding: WeatherSearchBinding? = null
    //get WeatherSearchViewModel instance
    private val weatherSearchViewModel: WeatherSearchViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = WeatherSearchBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weatherSearchViewModel.weatherData.observe(viewLifecycleOwner){
            if(it != null){
                // Use Weather Data exist navigate to details screen
                findNavController().navigate(R.id.search_to_details)
            }else{
                // Use Weather Data does exist Inform user
               showToast("Entered city name is not Valid")
            }
        }
        binding.city.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // checking the size of enter city name for enabling or disable search button
                val cityNameSize = p0?.length ?: 0
                if(cityNameSize > 0){
                    //Search button clickable
                    makeSearchButtonClickable()
                }else{
                    //Search button not clickable
                    makeSearchButtonNonClickable()
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

        binding.searchButton.setOnClickListener {
            makeSearchButtonNonClickable()
            val cityName = binding.city.text.toString()
            //check if entered text is valid
            val test = weatherSearchViewModel.verifyCity(cityName)
            if(weatherSearchViewModel.verifyCity(cityName)){
                // if text is valid fetch weather data
                fetchCityWeather(cityName)
            }else{
                //If entered text is not valid display a toast
                showToast("Entered text is not valid. Try entering city name only in letters")

            }
            //clear city name after click on search
            binding.city.text.clear()
        }
    }

    fun makeSearchButtonClickable(){
        //Search button clickable
        binding.searchButton.alpha = 1F
        binding.searchButton.isClickable = true

    }

    fun makeSearchButtonNonClickable(){
        //Search button not clickable
        binding.searchButton.alpha = 0.5F
        binding.searchButton.isClickable = false

    }

    fun fetchCityWeather(city: String){
        //fetch weather data
        weatherSearchViewModel.fetchWeatherFromCity(city)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun showToast(text: String, duration: Int = Toast.LENGTH_LONG){
        // Toast needs to run on main thread
        CoroutineScope(Dispatchers.Main).launch {
        //Show toast
            Toast.makeText(context,text,duration).show()
        }
    }
}