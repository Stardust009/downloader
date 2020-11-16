package com.fs.test1.adapter

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import com.fs.test1.downloader.DownloadTaskRunnable
import com.fs.test1.R
import com.fs.test1.base.BaseViewHolder

class DownloadItemListAdapter(
    private val mContext: Context,
    private val runnableList: ArrayList<DownloadTaskRunnable>
) : RecyclerView.Adapter<DownloadItemListAdapter.ViewHolder>() {


    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(
            LayoutInflater.from(mContext).inflate(R.layout.download_item_layout, parent, false)
        )
        runnableList[viewType].downloadProgressListener = { downloadedPercent, downloadedSize ->
            (mContext as Activity).runOnUiThread {
                holder.downloadProgress.progress = downloadedPercent
                holder.downloadedFileSizeTv.text = downloadedSize.toString()
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        Log.d("ttt", "onBindViewHolder : " + position + "    " + holder)
        val task = runnableList[position]

        with(holder) {

            downloadFileNameTv.text = task.fileName
            downloadedFileSizeTv.text = task.downloadedFileSize.toString()
            downloadProgress.tag = task.fileName
            if (task.downloadedFileSize == task.totalFileSize) downloadProgress.progress = 100
        }
    }

    override fun getItemCount(): Int {

        return runnableList.size
    }

    fun addTask(task: DownloadTaskRunnable) {
        runnableList.add(task)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) :BaseViewHolder(itemView){

        val downloadFileNameTv = bindView<TextView>(R.id.download_file_name)
        val downloadedFileSizeTv = bindView<TextView>(R.id.downloaded_size_and_all_size)
        val downloadProgress = bindView<ProgressBar>(R.id.download_progress)


    }

}