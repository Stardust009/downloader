package com.fs.test1.activity

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Environment
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.fs.test1.DEFAULT_DOWNLOAD_PATH
import com.fs.test1.R
import com.fs.test1.broadcast.NetworkStateReceiver
import com.fs.test1.fragment.DownloadHistoryFragment
import com.fs.test1.fragment.DownloadingFragment
import com.fs.test1.fragment.NewDownloadFragment
import com.fs.test1.service.DownloadService
import kotlinx.android.synthetic.main.donwload_fragment_holder_layout.*

class MainActivity : AppCompatActivity() {


    private val downloadingFragment: DownloadingFragment = DownloadingFragment()
    private val newDownloadFragment: NewDownloadFragment = NewDownloadFragment()
    private val downloadHistoryFragment = DownloadHistoryFragment()
    private lateinit var oldFragment: Fragment

    private val receiver: NetworkStateReceiver by lazy { NetworkStateReceiver() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(R.layout.donwload_fragment_holder_layout)

        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED)

            getExternalFilesDir(null)?.let { DEFAULT_DOWNLOAD_PATH = it.absolutePath }


        supportFragmentManager.beginTransaction().add(R.id.fragment_container, newDownloadFragment)
            .add(R.id.fragment_container, downloadingFragment)
            .add(R.id.fragment_container, downloadHistoryFragment)
            .hide(downloadingFragment)
            .hide(downloadHistoryFragment)
            .commit()
        oldFragment = newDownloadFragment
        bottom_nav.setOnNavigationItemSelectedListener {
            val fragment = when (it.itemId) {
                R.id.new_download -> {
                    newDownloadFragment
                }
                R.id.downloading -> {
                    downloadingFragment
                }
                R.id.download_history -> {
                    downloadHistoryFragment
                }
                else -> newDownloadFragment
            }
            supportFragmentManager.beginTransaction().hide(oldFragment).show(fragment)
                .commit()
            oldFragment = fragment
            true
        }

        registerReceiver(receiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
        Intent(this, DownloadService::class.java).apply { stopService(this) }
    }
}