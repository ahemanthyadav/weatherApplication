# Weather Application

This application uses Open Weather api to show some of the weather data on the screen based on  city entered by user. Application adhered to MVVM architecture and Jetpack Components like Flows, LiveData, Navigation, State Flow etc.

+ Kotlin was mostly used for development with some components implemented in Java. Java Repository used RxJava for sending data to ViewModels. 

+ Image caching was done to speed up loading of images using coil library and StateFlow. 

+ Hilt was used for Dependency injection for both application and also for unit testing

+ Junit 4 and Espresso for Unit testing

+ Rx Java, Coroutines with flow, liveData and state flow are used for network concurrency

+ Retrofit, OkHttps were used for network calls with HttpLoggingInterceptor  for monitoring Http Calls

+ DataStore was used for saving last searched city name to memory

+ Location permission will be requested at the start of the application every time if they are not granted

+ If location permission is provided current weather details are shown automatically using user location information for the first time when app is launched

+ If location permission is denied past searched   city weather details are shown automatically for the first time when app is launched

+ letters and spaces are allowed in city name

+ Search city value in memory will be reset when user click on the search again button or api call says city name is invalid
<img src="/appimages/app.gif" width="250" height="500"/>

