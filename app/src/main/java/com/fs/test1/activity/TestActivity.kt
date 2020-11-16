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
    }
}