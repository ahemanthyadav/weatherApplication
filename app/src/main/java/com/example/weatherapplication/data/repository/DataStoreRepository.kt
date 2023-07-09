package com.example.weatherapplication.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataStoreRepository @Inject constructor(private val dataStore: DataStore<Preferences>) {
    companion object {
        // city name key generation using stringPreferencesKey
        private val CITY_NAME_KEY = stringPreferencesKey("city_name")
    }

    val getLatestCityNameFlow: Flow<String?>
    // function to get latest city name from data store
        get() = dataStore.data.map { preferences ->
            preferences[CITY_NAME_KEY]
        }

    suspend fun saveLatestCityName(cityName: String) {
        // function to save latest city name to data store
        dataStore.edit { preferences ->
            preferences[CITY_NAME_KEY] = cityName
        }
    }

}