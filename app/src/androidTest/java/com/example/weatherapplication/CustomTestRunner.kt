package com.example.weatherapplication

import android.app.Application
import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.runner.AndroidJUnitRunner
import dagger.hilt.android.testing.HiltTestApplication

class CustomTestRunner: AndroidJUnitRunner() {
    //needed for using hilt in unit testing
    override fun newApplication(cl: ClassLoader?, name: String?, context: Context?): Application {
        return super.newApplication(cl, HiltTestApplication::class.java.name, context)
    }
}