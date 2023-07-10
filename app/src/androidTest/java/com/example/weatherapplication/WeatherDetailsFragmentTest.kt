package com.example.weatherapplication

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.weatherapplication.ui.MainActivity
import com.example.weatherapplication.ui.fragments.WeatherDetailsFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@LargeTest
class WeatherDetailsFragmentTest {





    @get:Rule(order = 0)
    val activityRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)
    @get:Rule(order = 1)
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        // Any setup or initialization code before each test

    }


    @Test
    fun testWeatherDetailsFragmentInitialized() {
        // Launch the Fragment
        val fragmentArgs = bundleOf("selectedListItem" to 0)
        val scenario = launchFragmentInContainer<WeatherDetailsFragment>( fragmentArgs)

        // Verify the initial state.
       // scenario.moveToState(Lifecycle.State.CREATED)


    }

    @Test
    fun testWeatherDetailsFragmentInitializedlaunch() {
        // Launch the Fragment
        val fragmentArgs = bundleOf("selectedListItem" to 0)
        val scenario = launchFragment<WeatherDetailsFragment>( fragmentArgs)

        // Verify the initial state.
        // scenario.moveToState(Lifecycle.State.CREATED)


    }
}