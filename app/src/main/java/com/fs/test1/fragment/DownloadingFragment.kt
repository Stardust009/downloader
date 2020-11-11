package com.fs.test1.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.fs.test1.R
import com.fs.test1.TaskManager
import com.fs.test1.adapter.DownloadItemListAdapter
import kotlinx.android.synthetic.main.downloading_page_layout.*

class DownloadingFragment: Fragment() {

    private lateinit var mRootView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mRootView = inflater.inflate(R.layout.downloading_page_layout, container, false)
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        with(downloading_task_list) {
            adapter = DownloadItemListAdapter(activity!!, TaskManager.getDownloadingTaskList())
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        }
    }
}