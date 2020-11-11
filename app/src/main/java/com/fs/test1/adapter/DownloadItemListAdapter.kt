package com.fs.test1.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import com.fs.test1.DownloadTaskRunnable
import com.fs.test1.R

class DownloadItemListAdapter(
    private val mContext: Context,
    val runnableList: ArrayList<DownloadTaskRunnable>
) : RecyclerView.Adapter<DownloadItemListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(mContext).inflate(R.layout.download_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return runnableList.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val downloadFileNameTv = bindView<TextView>(R.id.download_file_name)
        val downloadedFileSizeTv = bindView<TextView>(R.id.downloaded_size_and_all_size)
        val downloadProgress = bindView<ProgressBar>(R.id.download_progress)

        private inline fun <reified T : View> bindView(@IdRes id: Int): T =
            itemView.findViewById(id)
    }

}