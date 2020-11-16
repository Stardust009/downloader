package com.fs.test1.util.network

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.fs.test1.TAG
import java.lang.StringBuilder


object NetworkStateUtil {


    var NETWORK_STATE = NetworkState.NONE
    private val networkStateChangeListenerList = arrayListOf<NetworkStateChangeListener>()

    fun checkNetworkState(mContext: Context) {

        val connectionManager =
            (mContext.getSystemService(Context.CONNECTIVITY_SERVICE)) as ConnectivityManager
        val networkInfo = connectionManager.activeNetworkInfo
        NETWORK_STATE = if (networkInfo != null && networkInfo.isConnected) {
            when (networkInfo.type) {
                ConnectivityManager.TYPE_MOBILE -> {
                    Log.d(TAG, "当前使用移动数据")
                    NetworkState.MOBILE
                }
                ConnectivityManager.TYPE_WIFI -> {
                    Log.d(TAG, "当前使用WIFI网络")
                    NetworkState.WIFI

                }
                else -> {
                    Log.d(TAG, "当前网络未知")
                    NetworkState.UNKNOWN
                }
            }
        } else NetworkState.NONE
    }

    fun isConnect() = NETWORK_STATE == NetworkState.MOBILE ||  NETWORK_STATE == NetworkState.WIFI

    fun networkStateChange() {
        networkStateChangeListenerList.forEach { it.onChange(NETWORK_STATE) }
    }

    fun addNetworkStateChangeListener(listener: NetworkStateChangeListener) {
        networkStateChangeListenerList.add(listener)
    }


    private fun checkNetworkState2(mContext: Context) {

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
    interface NetworkStateChangeListener {

        fun onChange(state: NetworkState)
    }
}