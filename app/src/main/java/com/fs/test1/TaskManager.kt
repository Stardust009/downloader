package com.fs.test1

import android.util.Log
import com.fs.test1.adapter.DownloadItemListAdapter
import kotlin.collections.ArrayList

object TaskManager {

//    private val downloadingTaskList =
//        Collections.synchronizedList(arrayListOf<DownloadTaskRunnable>())

    private val downloadingTaskList = arrayListOf<DownloadTaskRunnable>()
    private val FinishedTaskList = ArrayList<String>()
     lateinit var adapter: DownloadItemListAdapter
    lateinit var updateDataSourceListener: (task: DownloadTaskRunnable) -> Unit
    fun getDownloadingTaskList() = downloadingTaskList

    fun addDownloadingTask(task: DownloadTaskRunnable) {
        downloadingTaskList.add(task)
        updateDataSourceListener.invoke(task)
        Log.d(TAG, "当前队列中任务数 --->   ${downloadingTaskList.size}")
    }

    fun setDownloadTaskListAdapter(adapter: DownloadItemListAdapter) {
        this.adapter = adapter
    }

    fun moveDownloadTask() {

    }
}