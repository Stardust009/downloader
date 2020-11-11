package com.fs.test1.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fs.test1.DOWNLOAD_URL
import com.fs.test1.R
import com.fs.test1.TAG
import com.fs.test1.broadcast.MyBroadcast
import com.fs.test1.service.DownloadService
import com.fs.test1.util.checkUrl
import kotlinx.android.synthetic.main.new_dowload_page.*
import java.util.*

class NewDownloadFragment : Fragment() {

    lateinit var mRootView: View

    private val downloadTaskList = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mRootView = inflater.inflate(R.layout.new_dowload_page, container, false)
        return mRootView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        download_url_edit.setText("https://cdn-file-ssl-android.ludashi.com/android/ludashi/ludashi_home.apk?t=1605063600")
        begin_download_bt.setOnClickListener {
            Log.d(TAG, "发送广播....")

            activity?.sendBroadcast(
                Intent().apply {
                    setClass(activity!!, MyBroadcast::class.java)
//                    action = "MY_BROADCAST_TEST"
                    putExtra("broadcast", "I am Broadcast.")
                })
            val url = download_url_edit.text.toString()
            checkDownloadUrl(url)
        }
    }

    private fun checkDownloadUrl(url: String?) {

        when {
            url.isNullOrEmpty() -> {
                showUrlIncorrect()
            }
            checkUrl(url) -> {
                download(url)
            }
            else -> showUrlIncorrect()
        }
    }


    private fun showUrlIncorrect() {
        Log.d(TAG, "出错。。。。。")
    }

    private fun download(url: String) {

        Intent(activity, DownloadService::class.java).also {
            it.putExtra(DOWNLOAD_URL, url)
            activity?.startService(it)
        }
    }
}