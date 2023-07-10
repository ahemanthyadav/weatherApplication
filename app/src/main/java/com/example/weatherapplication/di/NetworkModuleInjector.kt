package com.example.weatherapplication.di

import android.app.Application
import android.content.Context
import com.example.weatherapplication.Constants
import com.example.weatherapplication.data.repository.api.ApiClient
import com.example.weatherapplication.data.repository.api.ApiService
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

//Dagger Network module for providing dependency injection
@Module
@InstallIn(SingletonComponent::class)
class NetworkModuleInjector {


    @Provides
    @Singleton
    open fun providesContext(context: Application): Context {
        // provides application context
        return context
    }

    @Provides
    @Singleton
    open fun providesFusedLocationClient(context: Context): FusedLocationProviderClient {
        // provides application context
        return  LocationServices.getFusedLocationProviderClient(context)
    }
/*
    @Provides
    @Singleton
    open fun providesContext( @ApplicationContext context: Context): Context {
     //another method to provide application context
        return context
    }

 */


    // provides a Singleton Base Url through out application

    @Singleton
    @Provides
    fun provideBaseUrl(): String {
        return  Constants.baseURl
    }

    // provides a Singleton Moshi converter object through out application
    @Singleton
    @Provides
    fun provideMoshiConverterFactory(): Converter.Factory {
        // used for kotlin serialization and deserialization
        return MoshiConverterFactory.create()
    }

    // provides a Singleton Interceptor object through out application
    @Singleton
    @Provides
    fun provideHttpInterceptor(): HttpLoggingInterceptor {
        // adding HttpLoggingInterceptor for monitoring network calls for debugging
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }
    // provides a Singleton OkHttpClient object with Interceptor through out application
    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        // provides timeout and
        val httpBuilder = OkHttpClient.Builder().apply {
            // adding interceptor to httpBuilder
            addInterceptor(interceptor)
            //set read time out to 60 seconds
            readTimeout(Constants.socketTimeout, TimeUnit.SECONDS)
        }
        return httpBuilder.build()
    }

    // provides a Singleton Retrofit object through out application
    @Singleton
    @Provides
    fun provideRetrofit(
        baseUrl: String,
        moshiConverterFactory: Converter.Factory,
        okHttpClient: OkHttpClient
    ): Retrofit{
        return  Retrofit.Builder().apply {
            //adding baseurl, moshi converter and Okhttp client to retrofit
            baseUrl(baseUrl)
            addConverterFactory(moshiConverterFactory)
            //addConverterFactory(MoshiConverterFactory.create())
            //addConverterFactory(GsonConverterFactory.create())
            client(okHttpClient)
        }.build()
    }

    @Singleton
    @Provides
    // provides a Singleton ApiService object through out application
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    // provides a Singleton apiClient object through out application
    fun provideApiClient(retrofit: Retrofit): ApiClient {
        return retrofit.create(ApiClient::class.java)
    }


}