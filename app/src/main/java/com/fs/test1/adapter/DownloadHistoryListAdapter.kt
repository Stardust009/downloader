package com.fs.test1.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fs.test1.R
import com.fs.test1.base.BaseViewHolder
import com.fs.test1.pojo.DownloadHistoryPoJo

class DownloadHistoryListAdapter(private val mContext: Context, val downloadHistoryList: ArrayList<DownloadHistoryPoJo>): RecyclerView.Adapter<DownloadHistoryListAdapter.ViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.download_history_item_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return downloadHistoryList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            fileNameTV.text = "文件名称: ${downloadHistoryList[position].fileName}"
            fileSizeTV.text = "文件大小: " + downloadHistoryList[position].fileSize + "MB"
            filePathTV.text = "文件路径L " + downloadHistoryList[position].filePath
        }
    }


    inner class ViewHolder(itemView: View): BaseViewHolder(itemView){

        val fileNameTV = bindView<TextView>(R.id.file_name_tv)
        val fileSizeTV = bindView<TextView>(R.id.file_size_tv)
        val filePathTV = bindView<TextView>(R.id.file_path_tv)
    }
}