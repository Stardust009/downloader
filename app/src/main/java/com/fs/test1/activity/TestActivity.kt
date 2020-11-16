package com.fs.test1.activity

import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.fs.test1.*
import com.fs.test1.adapter.DownloadItemListAdapter
import com.fs.test1.content_provider.DownloadHistoryContentProvider
import com.fs.test1.downloader.TaskManager
import kotlinx.android.synthetic.main.downloading_page_layout.*

class TestActivity : AppCompatActivity() {

    private lateinit var mAdapter: DownloadItemListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.downloading_page_layout)


/*        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED)

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

        }
        */

        val name = "ludashi.apk"
        val path = "/com.fs.test1/downhload"
        val writable = DownloadHistoryContentProvider.db.writableDatabase
        writable.execSQL("insert into download_history (file_name, file_size, file_path) values ('$name', '123434', '$path')")
    }
}