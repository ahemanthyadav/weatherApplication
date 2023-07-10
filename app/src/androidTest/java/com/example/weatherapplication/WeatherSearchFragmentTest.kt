package com.example.weatherapplication

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.weatherapplication.ui.MainActivity
import com.example.weatherapplication.ui.fragments.WeatherDetailsFragment
import com.example.weatherapplication.ui.fragments.WeatherSearchFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.regex.Pattern.matches


@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@LargeTest
class WeatherSearchFragmentTest {


    //manages the components' state
    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)
    @get:Rule(order = 1)
    val activityRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setup() {
        // Any setup or initialization code before each test

    }


    @Test
    fun testFragmentDisplayInitialized() {
        // Launch the Fragment

        val scenario = launchFragmentInContainer<WeatherDetailsFragment>(  initialState = Lifecycle.State.INITIALIZED)

        // Verify the initial state.
        scenario.moveToState(Lifecycle.State.INITIALIZED)
        // Verify that a specific view is displayed
        //onView(withId(R.id.guideline_middle)).check(matches(isDisplayed()))

        // Perform actions and assertions as needed
        // Example actions:
        // - Perform a click action: onView(withId(R.id.button_id)).perform(click())
        // - Enter text: onView(withId(R.id.edit_text_id)).perform(typeText("Example"))

        // Example assertions:
        // - Verify text displayed: onView(withId(R.id.text_view_id)).check(matches(withText("Hello")))
        // - Verify visibility: onView(withId(R.id.view_id)).check(matches(isDisplayed()))
    }
    @Test
    fun testFragmentDisplayInitializedCreated() {
        // Launch the Fragment

        val scenario = launchFragmentInContainer<WeatherSearchFragment>(  initialState = Lifecycle.State.INITIALIZED)

        // Verify fragment created
        scenario.moveToState(Lifecycle.State.CREATED)

    }
    @Test
    fun testFragmentDisplay() {
        // Launch the Fragment

        val scenario = launchFragmentInContainer<WeatherSearchFragment>(  initialState = Lifecycle.State.INITIALIZED)

        // Verify the initial state.
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Verify that a specific view is displayed
        //onView(withId(R.id.guideline_middle)).check(matches(isDisplayed()))

        // Perform actions and assertions as needed
        // Example actions:
        // - Perform a click action: onView(withId(R.id.button_id)).perform(click())
        // - Enter text: onView(withId(R.id.edit_text_id)).perform(typeText("Example"))

        // Example assertions:
        // - Verify text displayed: onView(withId(R.id.text_view_id)).check(matches(withText("Hello")))
        // - Verify visibility: onView(withId(R.id.view_id)).check(matches(isDisplayed()))
    }

    @Test fun testEventFragmentRecreate() {
        val scenario = launchFragmentInContainer<WeatherSearchFragment>()
        scenario.recreate()

    }
    @Test
    fun testFragmentDisplay1() {
        // The "fragmentArgs" arguments are optional.
        val fragmentArgs = bundleOf("numElements" to 0)
        // Launch the Fragment
        val scenario1 = launchFragment<WeatherSearchFragment>(fragmentArgs)
        //val scenario = launchFragmentInContainer<WeatherSearchFragment>()

        // Verify that a specific view is displayed
        //onView(withId(R.id.guideline_middle)).check(matches(isDisplayed()))

        // Perform actions and assertions as needed
        // Example actions:
        // - Perform a click action: onView(withId(R.id.button_id)).perform(click())
        // - Enter text: onView(withId(R.id.edit_text_id)).perform(typeText("Example"))

        // Example assertions:
        // - Verify text displayed: onView(withId(R.id.text_view_id)).check(matches(withText("Hello")))
        // - Verify visibility: onView(withId(R.id.view_id)).check(matches(isDisplayed()))
    }




}