package com.example.weatherapplication

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.weatherapplication.data.model.WeatherNetworkModel
import com.example.weatherapplication.data.repository.DataStoreRepository
import com.example.weatherapplication.data.repository.NetworkRepository
import dagger.hilt.android.testing.CustomTestApplication
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject


//responsible for generating Hilt components for each test.

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@LargeTest
class NetworkRepositoryTest() {
    //private lateinit var networkRepository: NetworkRepository
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var testCoroutineScope: TestScope

    //manages the components' state
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var networkRepository: NetworkRepository

    @Before
    fun setupRepository(){
        //testCoroutineScope = TestCoroutineScope(testDispatcher)
        hiltRule.inject()
    }

    @Test
    fun testNetworkRepositoryInitialized(){
        //testing if d
        Assert.assertNotNull(networkRepository)
    }

    @Test
    fun testNetworkRepositoryVerifyCity(){
        //testing if d

        Assert.assertEquals(networkRepository.verifyCityName("city1"),false)
        Assert.assertEquals(networkRepository.verifyCityName("city"),true)
        Assert.assertEquals(networkRepository.verifyCityName("ci t y 1"),false)
        Assert.assertEquals(networkRepository.verifyCityName("city$#1"),false)
        Assert.assertEquals(networkRepository.verifyCityName("ci t y"),true)
        Assert.assertEquals(networkRepository.verifyCityName("detroit"),true)
        Assert.assertEquals(networkRepository.verifyCityName("ci t y o bt"),true)
    }

    @Test
    fun testNetworkRepositoryCheckIfAllCharacters(){
        //testing if d

        Assert.assertEquals(networkRepository.checkIfAllCharacters("city1"),false)
        Assert.assertEquals(networkRepository.checkIfAllCharacters("city"),true)
        Assert.assertEquals(networkRepository.checkIfAllCharacters("ci t y 1"),false)
        Assert.assertEquals(networkRepository.checkIfAllCharacters("city$#1"),false)
        Assert.assertEquals(networkRepository.checkIfAllCharacters("ci t y"),true)
        Assert.assertEquals(networkRepository.checkIfAllCharacters("detroit"),true)
        Assert.assertEquals(networkRepository.checkIfAllCharacters("ci t y o bt"),true)
    }



    @Test
    fun testNetworkRepositoryCheckIsStringEmpty(){
        //testing if d

        Assert.assertEquals(networkRepository.checkIsStringEmpty(""),true)
        Assert.assertEquals(networkRepository.checkIsStringEmpty("city"),false)

    }

    @Test
    fun testNetworkRepositoryWeatherData(){
        //testing if d
        runTest {

            val result =   networkRepository.getWeatherFromCity("detroit")
            println(result)
            //check network data is not null
            Assert.assertNotNull(result)
            //Assert.assertEquals( result::class, WeatherNetworkModel::class)

        }
        //networkRepository.getWeatherFromCity("detroit")
        Assert.assertEquals(networkRepository.checkIsStringEmpty(""),true)
        Assert.assertEquals(networkRepository.checkIsStringEmpty("city"),false)

    }




}