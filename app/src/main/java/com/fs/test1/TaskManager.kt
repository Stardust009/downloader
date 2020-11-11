package com.fs.test1

import com.fs.test1.adapter.DownloadItemListAdapter
import java.util.*
import kotlin.collections.ArrayList

object TaskManager {

//    private val downloadingTaskList =
//        Collections.synchronizedList(arrayListOf<DownloadTaskRunnable>())

    private val downloadingTaskList = arrayListOf<DownloadTaskRunnable>()
    private val FinishedTaskList = ArrayList<String>()
    private lateinit var adapter: DownloadItemListAdapter

    fun getDownloadingTaskList() = downloadingTaskList

    fun addDownloadingTask(task: DownloadTaskRunnable) {
        downloadingTaskList.add(task)
        adapter.notifyDataSetChanged()
    }

    fun setDownloadTaskListAdapter(adapter: DownloadItemListAdapter) {
        this.adapter = adapter
    }
    fun moveDownloadTask()
}