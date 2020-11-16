package com.fs.test1.base

import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView

open class BaseViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {

     protected inline fun <reified T : View> bindView(@IdRes id: Int): T =
        itemView.findViewById(id)
}