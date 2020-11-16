package com.fs.test1.fragment

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.fs.test1.R
import com.fs.test1.TAG
import com.fs.test1.adapter.DownloadHistoryListAdapter
import com.fs.test1.downloader.TaskManager
import com.fs.test1.pojo.DownloadHistoryPoJo
import kotlinx.android.synthetic.main.download_history_page_layout.*

class DownloadHistoryFragment: Fragment() {

    private lateinit var mAdapter: DownloadHistoryListAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val uri = Uri.parse("content://com.fs.test1.content_provider.my_provider/download_history")
        val cursor = activity!!.contentResolver.query(
            uri,
            arrayOf("file_name", "file_size", "file_path"),
            null,
            null,
            null
        )
        while (cursor?.moveToNext() == true) {
//            cursor.columnNames.forEach { Log.d(TAG, "$it   ") }
//            Log.d(TAG, cursor.getString(0) + "" + cursor.getString(1) + cursor.getString(2))
            TaskManager.downloadHistoryList.add(
                DownloadHistoryPoJo(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2)
                )
            )
        }

        return inflater.inflate(R.layout.download_history_page_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {



        with(download_history_recycler_view) {
            mAdapter = DownloadHistoryListAdapter(activity!!, TaskManager.downloadHistoryList)
            adapter = mAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        }

        TaskManager.updateDownloadHistory =
            { activity?.runOnUiThread { mAdapter.notifyDataSetChanged() } }
    }
}