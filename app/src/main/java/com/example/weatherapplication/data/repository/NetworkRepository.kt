package com.example.weatherapplication.data.repository

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Environment
import android.util.Log
import androidx.core.graphics.drawable.toBitmap
import coil.ImageLoader
import coil.request.Disposable
import coil.request.ImageRequest
import com.example.weatherapplication.Constants
import com.example.weatherapplication.data.model.WeatherNetworkModel
import com.example.weatherapplication.data.repository.api.ApiClient
import com.example.weatherapplication.data.repository.api.ApiService
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkRepository @Inject constructor(private val apiService: ApiClient, private val context: Context) {

    //As business logic is small, for simplicity  using Repository as Data Layer as well not
    val TAG = "NetworkRepositoryResponse Log"
    private  var latestWeatherData: WeatherNetworkModel? = null
    //val fusedLocationClient: FusedLocationProviderClient

    //private var imageDrawable: Drawable? = null

    lateinit var disposable: Disposable

    // using state flow for tracking downloaded image as flow is not supported by coil
    // tried global variable and flow methods have timing issues and need more work
    private val _weatherImage = MutableStateFlow<Drawable?>(null)
    val weatherImage: StateFlow<Drawable?> = _weatherImage

    private fun updateWeatherImage(image: Drawable?) {
        _weatherImage.value = image
    }

    //Repository call for getting weather data
    suspend fun getWeatherFromCity(city: String): Flow<WeatherNetworkModel?> {
        // Weather data is sent as flow to viewModel for UI Consumption
        return flow<WeatherNetworkModel?> {
            var weatherData: WeatherNetworkModel? = null
            //println("weather NetworkRepository: $city")
            val request = apiService.getWeatherFromCity( city,Constants.apiValue)
            val response = request.execute()
           Log.d(TAG,response.toString())
                       when(response.code()){
                           200 -> {
                               //Api call is success
                               //updating weatherModel data with latest network data
                               weatherData = response.body()
                               weatherData?.let{
                                   /*save to global variable for Details screen consumption.
                                    latestWeatherData variable is reset after details screen consumed it

                                    */

                                   latestWeatherData = it
                                   //generating image url
                                   generateImageURL(it)
                               }
                           }

                           401 ->{
                               //  Unauthorized Error
                               Log.v(TAG, response.code().toString())
                               Log.v(TAG,response.message())

                           }
                           404 ->{
                               //  Not Found Error
                               Log.v(TAG, response.code().toString())
                               Log.v(TAG,response.message())
                           }
                           429 ->{
                               // Too Many Requests
                               Log.v(TAG, response.code().toString())
                               Log.v(TAG,response.message())
                           }
                           in 500..599 ->{
                               // Unexpected Error
                               Log.v(TAG, response.code().toString())
                               Log.v(TAG,response.message())
                           }
                           else ->{
                               // Network Error
                               Log.v(TAG, response.code().toString())
                               Log.v(TAG,response.message())
                           }
                       }
            emit(weatherData)
        }.flowOn(Dispatchers.IO)
    }


    fun getStoredWeatherNetworkData(): WeatherNetworkModel?{
        //function to get last searched data in memory
        // check if latestResponseData is initialized
        val localWeatherData = latestWeatherData?.copy()
        //reset latestWeatherData Data to null so Transition  between search and details can happen
        latestWeatherData = null
        return localWeatherData
    }
    /*
     fun getStoredImage(): Drawable?{
        //function to get last searched weather image in ram memory
        // Create a copy of the original drawable
        val copiedDrawable: Drawable? = imageDrawable?.constantState?.newDrawable()

        //reset imageDrawable to null so Transition  between search and details can happen
        imageDrawable = null
        return copiedDrawable
    }




    suspend fun sendDrawableAsFlow(): Flow<Drawable>{
        //function to consume drawable image as flow before or after the details viewModel produced
        return flow<Drawable>{
            // Create a copy of the original drawable
            val copiedDrawable: Drawable? = getStoredImage()?.constantState?.newDrawable()
            //copiedDrawable = image
            //emit(image)
            copiedDrawable?.let {
                emit(it)
            }
            //Adding buffer to make sure data is available till details view model can consume
            //Setting buffer size as 1
        }.apply{
            flowOn(Dispatchers.IO)
            buffer(1)
        }
    }

     */

    /*
   suspend fun sendDrawableAsFlow1(image: Drawable){
        //function to consume drawable image as flow
        // Create a copy of the original drawable
        flow<Drawable>{
            // Create a copy of the original drawable
            val copiedDrawable: Drawable? = image.mutate().constantState?.newDrawable()
            //copiedDrawable = image
            //emit(image)
            copiedDrawable?.let { emit(it) }
        }.flowOn(Dispatchers.IO).apply{
            //Adding buffer to make sure data is available till details view model can consume
            //Setting buffer size as 1
            buffer(1)
        }
    }

 */
    fun verifyCityName(city: String) = !checkIsStringEmpty(city) and checkIfAllCharacters(city)
    fun checkIsStringEmpty(city: String) = city.isEmpty()
    fun checkIfAllCharacters(city: String) = city.allLettersAndSpace()

    //Extension function for checking all letters and space
    fun String.allLettersAndSpace(): Boolean = this.matches(Regex("^[A-Za-z\\s]+$"))

    fun generateImageURL(weatherNetworkModel: WeatherNetworkModel) = "${Constants.imageURl}/img/wn/${weatherNetworkModel.weather[0].icon}@2x.png".also{
        debugLog(it)
        println("imageUrl:: $it")
        CoroutineScope(Dispatchers.IO).launch {
            imageCache(it)
        }
    }

    fun debugLog(debugText: String) = Log.d(TAG,debugText)
     suspend fun imageCache(url: String?) {
        url?.let {

            //val imageLoader = ImageLoader(context)
            val request = ImageRequest.Builder(context)
                .data(url)
                .target { weatherImageDrawable ->
                    // saving the image obtained from internet to global variable
                   // imageDrawable = weatherImageDrawable
                    updateWeatherImage(weatherImageDrawable)
                }
                .build()
             disposable = ImageLoader(context).enqueue(request)
        }
    }
}