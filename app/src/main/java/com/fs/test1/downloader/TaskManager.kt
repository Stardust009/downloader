package com.fs.test1.downloader

import android.util.Log
import com.fs.test1.TAG
import com.fs.test1.pojo.DownloadHistoryPoJo
import kotlin.collections.ArrayList

object TaskManager {

    private val downloadingTaskList = ArrayList<DownloadTaskRunnable>()
    val FinishedTaskList = ArrayList<DownloadTaskRunnable>()
    val downloadHistoryList = ArrayList<DownloadHistoryPoJo>()


    lateinit var updateDataSource: () -> Unit
    lateinit var updateDownloadHistory: () -> Unit
    fun getDownloadingTaskList() =
        downloadingTaskList
    fun addDownloadingTask(task: DownloadTaskRunnable) {
        downloadingTaskList.add(task)
        updateDataSource.invoke()
    }

    fun addDownloadHistory(downloadHistoryPoJo: DownloadHistoryPoJo) {
        downloadHistoryList.add(downloadHistoryPoJo)
        Log.d(TAG, "添加历史记录...")
        updateDownloadHistory.invoke()
    }
}