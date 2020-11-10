package com.fs.test1.activity

import android.os.Bundle
import android.os.Environment
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.fs.test1.DEFAULT_DOWNLOAD_PATH
import com.fs.test1.R
import com.fs.test1.fragment.DownloadHistoryFragment
import com.fs.test1.fragment.DownloadingFragment
import com.fs.test1.fragment.NewDownloadFragment
import kotlinx.android.synthetic.main.donwload_fragment_holder_layout.*

class MainActivity : AppCompatActivity() {

    private val downloadingFragment by lazy { DownloadingFragment() }
    private val newDownloadFragment by lazy { NewDownloadFragment() }
    private val downloadHistoryFragment by lazy { DownloadHistoryFragment() }


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
            .commit()

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
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
                .commit()
            true
        }

    }
}