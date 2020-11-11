package com.fs.test1.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.fs.test1.R
import java.io.File
import com.fs.test1.TAG
import kotlinx.android.synthetic.main.download_item_layout.*
import kotlinx.android.synthetic.main.test_layout.*
import java.net.URL

class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.download_item_layout)

        bt.setOnClickListener {
            download_progress.progress += 10
        }

        Thread {
            val httpUrl = URL("http://dl.2345.com/haozip/2345haozip_6.2.0.11032_setup.exe")
            val conn = httpUrl.openConnection()
            val contentLength = conn.getHeaderField("Date") ?: ""
            Log.d(TAG, "ContentLength --------  $contentLength")

            if (contentLength.isEmpty()) {
                Log.d(TAG, "无效文件。。。。")
            } else {
                Log.d(TAG, "有效文件 -----> ContentLength --------  $contentLength")
            }
        }.start()
    }
}