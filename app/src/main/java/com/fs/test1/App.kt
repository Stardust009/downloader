package com.fs.test1

import android.app.Application
import android.util.Log


class App: Application() {

    companion object{

         lateinit var AppInstance: App

    }

    override fun onCreate() {
        super.onCreate()
        Log.d("TAG", "Application 初始化")
        AppInstance = this
    }
}