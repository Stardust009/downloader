package com.fs.test1.service

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.fs.test1.DOWNLOAD_URL
import com.fs.test1.Downloader
import com.fs.test1.TAG
import com.fs.test1.activity.SecondActivity
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class DownloadService: Service() {


    private val downloader = Downloader()

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        // 线程池
//        threadPool = ThreadPoolExecutor(2, 10, 5000, TimeUnit.SECONDS, )
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {


        val downloadUrl = intent?.getStringExtra(DOWNLOAD_URL)!!
        Log.d(TAG, "URL ---->  $downloadUrl")
        downloader.download(downloadUrl, {
            Log.d(TAG, "无效的下载")
        }, {
            Log.d(TAG, "开始下载。。。。")
        }, {

        })
//        val pendingIntent = Intent(this, SecondActivity::class.java).let {
//            PendingIntent.getService(this, 0, it, 0)
//        }

        return START_NOT_STICKY
    }

}