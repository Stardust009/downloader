package com.fs.test1.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.fs.test1.R
import com.fs.test1.TAG
import com.fs.test1.TaskManager
import com.fs.test1.adapter.DownloadItemListAdapter
import kotlinx.android.synthetic.main.downloading_page_layout.*

class DownloadingFragment : Fragment() {

    private lateinit var mRootView: View
    private lateinit var mAdapter: DownloadItemListAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "初始化 DownloadingFragment  onCreateView ")

        mRootView = inflater.inflate(R.layout.downloading_page_layout, container, false)
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        Log.d(TAG, "初始化 DownloadingFragment  onViewCreated ")
        downloading_task_list.apply {
            mAdapter = DownloadItemListAdapter(activity!!, TaskManager.getDownloadingTaskList())
            adapter = mAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        }

        with(TaskManager) {
            setDownloadTaskListAdapter(mAdapter)
            updateDataSourceListener = {
                activity?.runOnUiThread {
                    mAdapter.notifyDataSetChanged()
                    downloading_task_list.scrollToPosition(mAdapter.itemCount  -1)
                    Log.d(TAG, "更新。。。${Thread.currentThread().name}")
                }
            }
        }
    }
}