package com.example.weatherapplication

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.runner.lifecycle.ActivityLifecycleCallback
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import com.example.weatherapplication.ui.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)
    // track
    private lateinit var scenario: ActivityScenario<MainActivity>
    private val activityLifecycleObserver =
        ActivityLifecycleCallback { activity, stage ->
            when (stage) {
                Stage.PRE_ON_CREATE -> {
                    if (activity is MainActivity) {
                        // activity.mainActivityViewModel = viewModel

                    }
                }
                Stage.CREATED -> {
                    if (activity is MainActivity) {

                    }
                }
                else -> {
                    // no-op
                }
            }
        }

    @Before
    fun setUp() {
        ActivityLifecycleMonitorRegistry.getInstance()
            .addLifecycleCallback(activityLifecycleObserver)
    }

    @After
    fun tearDown() {
        ActivityLifecycleMonitorRegistry.getInstance()
            .removeLifecycleCallback(activityLifecycleObserver)
        scenario.close()
    }


    @Test
    fun testActivityDisplayed() {
        // Given
        //val displayString = "Sample String"
        // When
        scenario = launchActivity()
        scenario.onActivity { activity ->
            // do some stuff with the Activity
            //activity.setTitle(displayString)
        }
        //Thread.sleep(2000)
        // Then
        Espresso.onView(withId(R.id.main_activity_layout))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }


}