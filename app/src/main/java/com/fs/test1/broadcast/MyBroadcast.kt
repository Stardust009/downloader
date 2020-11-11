package com.fs.test1.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.fs.test1.TAG

class MyBroadcast: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d(TAG, "广播接收器")
        intent?.let { Log.d(TAG, it.getStringExtra("broadcast") ?: "Intent 为空") }
    }
}