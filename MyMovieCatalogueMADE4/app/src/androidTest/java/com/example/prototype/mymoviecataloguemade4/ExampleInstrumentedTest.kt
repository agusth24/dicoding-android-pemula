package com.example.prototype.mymoviecataloguemade4

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@Suppress("DEPRECATION")
@RunWith(AndroidJUnit4ClassRunner ::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.prototype.mymoviecataloguemade4", appContext.packageName)
    }
}
