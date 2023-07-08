package com.example.weatherapplication

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.runner.lifecycle.ActivityLifecycleCallback
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import com.example.weatherapplication.ui.MainActivity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
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
    fun StartswithoutCrashing() {
        // test if activity launches sucessfully
        scenario = launchActivity()
    }

}