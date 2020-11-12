package com.fs.test1.activity

import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.fs.test1.*
import com.fs.test1.adapter.DownloadItemListAdapter
import kotlinx.android.synthetic.main.downloading_page_layout.*

class TestActivity : AppCompatActivity() {

    private lateinit var mAdapter: DownloadItemListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.downloading_page_layout)


        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED)

            getExternalFilesDir(null)?.let { DEFAULT_DOWNLOAD_PATH = it.absolutePath }
        downloading_task_list.apply {
            mAdapter =
                DownloadItemListAdapter(this@TestActivity, TaskManager.getDownloadingTaskList())
            adapter = mAdapter
            layoutManager =
                LinearLayoutManager(this@TestActivity, LinearLayoutManager.VERTICAL, false)
        }

        with(TaskManager) {
//            setDownloadTaskListAdapter(mAdapter as DownloadItemListAdapter)
            updateDataSourceListener = {
                this@TestActivity.runOnUiThread {
                    mAdapter.notifyDataSetChanged()
                    downloading_task_list.scrollToPosition(mAdapter.itemCount - 1)
                    Log.d(TAG, "更新。。。${Thread.currentThread().name}")
                }
            }
        }

        with(TaskManager) {
            setDownloadTaskListAdapter(mAdapter)
            updateDataSourceListener = {
                runOnUiThread {
                    mAdapter.notifyDataSetChanged()
                    downloading_task_list.scrollToPosition(mAdapter.itemCount - 1)
                    Log.d(TAG, "更新。。。${Thread.currentThread().name}")
                }
            }
        }
/*
        update_bt.setOnClickListener {
            Downloader.getInstance().download(
                "https://cdn-file-ssl-android.ludashi.com/android/ludashi/ludashi_home.apk?t=1605063600",
                {},
                {},
                {})

        }*/
    }
}