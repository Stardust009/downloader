package com.fs.test1.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.fs.test1.DOWNLOAD_CONFIG
import com.fs.test1.DOWNLOAD_URL
import com.fs.test1.R
import com.fs.test1.content_provider.DownloadHistoryContentProvider
import com.fs.test1.downloader.DownloadConfig
import com.fs.test1.downloader.Downloader
import com.fs.test1.pojo.DownloadConfigPoJo
import com.fs.test1.pojo.DownloadProxyPoJo
import com.fs.test1.service.DownloadService
import com.fs.test1.util.checkUrl
import kotlinx.android.synthetic.main.new_dowload_page.*

class NewDownloadFragment : Fragment() {

    lateinit var mRootView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mRootView = inflater.inflate(R.layout.new_dowload_page, container, false)
        return mRootView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        Downloader.getInstance().downloadFinishListener = { name, size, path ->
            activity?.runOnUiThread {
                val writable = DownloadHistoryContentProvider.db.writableDatabase
                writable.execSQL("insert into download_history (file_name, file_size, file_path) values ('$name','$size','$path')")
            }
        }
        download_url_edit.setText("https://cdn-file-ssl-android.ludashi.com/android/ludashi/ludashi_home.apk?t=1605063600")
        begin_download_bt.setOnClickListener {
            val url = download_url_edit.text.toString()
                checkDownloadUrl(url)
        }
    }

    private fun generateDownloadConfig() {


    }

    private fun checkDownloadUrl(url: String?) {

        when {
            url.isNullOrEmpty() -> {
                Toast.makeText(activity, "URL不合法", Toast.LENGTH_SHORT).show()
            }
            checkUrl(url) -> {
                Intent(activity, DownloadService::class.java).also {
                    it.putExtra(DOWNLOAD_URL, url)
                    val builder = DownloadConfig.Builder()
                    val hostName = host_name_edit.text.toString()
                    val portNum = port_edit.text.toString()
                    if (hostName.isNotBlank() && portNum.isNotBlank()) {
                        builder.proxy(DownloadProxyPoJo(hostName, portNum.toInt()))
                    }
                    it.putExtra(DOWNLOAD_CONFIG, builder.build())
                    activity?.startService(it)
                }
            }
            else -> Toast.makeText(activity, "URL不合法", Toast.LENGTH_SHORT).show()
        }
    }


}