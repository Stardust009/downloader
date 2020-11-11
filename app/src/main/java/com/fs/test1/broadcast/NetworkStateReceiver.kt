package com.fs.test1.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.util.Log
import com.fs.test1.TAG
import com.fs.test1.util.NetworkState
import java.lang.StringBuilder

class NetworkStateReceiver : BroadcastReceiver() {


    companion object {
        var NETWORK_STATE = NetworkState.NONE
    }

    override fun onReceive(context: Context, intent: Intent?) {

        Log.d(TAG, "NetworkStateReceiver")
        getNetworkState(context)
    }

    private fun getNetworkState(mContext: Context) {
        val connectivityManager =
            mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        activeNetworkInfo?.let {
            if (activeNetworkInfo.isConnected) {
                if (activeNetworkInfo.type == ConnectivityManager.TYPE_WIFI) {
                    Log.d(TAG, "使用WIFI连接")
                    NETWORK_STATE = NetworkState.WIFI
                }
                NETWORK_STATE = if (activeNetworkInfo.type == ConnectivityManager.TYPE_MOBILE) {
                    Log.d(TAG, "使用移动数据")
                    NetworkState.MOBILE
                } else {
                    Log.d(TAG, "未知网络")
                    NetworkState.UNKNOWN
                }
            } else {
                Log.d(TAG, "网络异常")
                NETWORK_STATE = NetworkState.NONE
            }
        }

        activeNetworkInfo ?: let { NETWORK_STATE = NetworkState.NONE }
    }

    private fun checkNetworkState(mContext: Context) {

        val connectivityManager =
            mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networks = connectivityManager.allNetworks
        val sb = StringBuilder()
        for (i in networks.indices) {
            val networkInfo = connectivityManager.getNetworkInfo(networks[i])
            sb.append(networkInfo?.typeName + "  connect is " + networkInfo?.isConnected)
        }
        Log.d(TAG, sb.toString())
    }
}