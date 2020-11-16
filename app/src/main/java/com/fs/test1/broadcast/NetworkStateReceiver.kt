package com.fs.test1.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.util.Log
import com.fs.test1.TAG
import com.fs.test1.util.network.NetworkStateUtil
import java.lang.StringBuilder

class NetworkStateReceiver : BroadcastReceiver() {


    override fun onReceive(context: Context, intent: Intent?) {

        Log.d(TAG, "NetworkStateReceiver")
        NetworkStateUtil.checkNetworkState(context)
        NetworkStateUtil.networkStateChange()

    }
}