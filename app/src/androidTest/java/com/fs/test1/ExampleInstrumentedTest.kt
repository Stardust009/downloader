package com.fs.test1

import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import java.net.URL

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.fs.test1", appContext.packageName)
    }


    @Test
    fun netTest() {

        val httpUrl = URL("http://dl.2345.com/haozip/2345haozip_6.2.0.11032_setup.exe")
        val conn = httpUrl.openConnection()
        val contentLength = conn.getHeaderField("Date") ?: ""
        Log.d(TAG, "ContentLength --------  $contentLength")

        if (contentLength.isEmpty()) {
            Log.d(TAG, "无效文件。。。。")
        } else {
            Log.d(TAG, "有效文件 -----> ContentLength --------  $contentLength")
        }
    }
}