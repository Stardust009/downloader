package com.fs.test1.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import com.fs.test1.DOWNLOAD_URL
import com.fs.test1.downloader.Downloader
import com.fs.test1.TAG
import com.fs.test1.pojo.DownloadConfigPoJo
import com.fs.test1.util.network.NetworkState
import com.fs.test1.util.network.NetworkStateUtil

class DownloadService : Service() {

    private val waitDownloadUrlList = arrayListOf<String>()

    private val waitDownloadMap = LinkedHashMap<String, DownloadConfigPoJo>()
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {

        Log.d(TAG, "启动服务")
        NetworkStateUtil.addNetworkStateChangeListener(object :
            NetworkStateUtil.NetworkStateChangeListener {
            override fun onChange(state: NetworkState) {
                if (state == NetworkState.MOBILE || state == NetworkState.MOBILE) {
                    val iterator = waitDownloadUrlList.iterator()
                    while (iterator.hasNext()) {
                        val url = iterator.next()
                        Downloader.getInstance().download(url)
                        iterator.remove()
                    }
                }
            }
        })
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {


        val downloadUrl = intent?.getStringExtra(DOWNLOAD_URL)!!
        Log.d(TAG, "URL ---->  $downloadUrl")

        if (NetworkStateUtil.isConnect()) Downloader.getInstance().download(downloadUrl)
        else {
            Toast.makeText(this, "无网络", Toast.LENGTH_SHORT).show()
            waitDownloadUrlList.add(downloadUrl)
        }

        return START_NOT_STICKY
    }

}