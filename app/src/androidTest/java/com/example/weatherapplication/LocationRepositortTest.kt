package com.example.weatherapplication

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.weatherapplication.data.repository.LocationRepository
import dagger.hilt.android.testing.CustomTestApplication
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@LargeTest
class LocationRepositortTest() {


    //manages the components' state
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var locationRepository: LocationRepository




    @Before
    fun setupRepository(){
        hiltRule.inject()
    }

    @Test
    fun testDatastoreInitialized(){
        //testing if d
        Assert.assertNotNull(locationRepository)
    }
}