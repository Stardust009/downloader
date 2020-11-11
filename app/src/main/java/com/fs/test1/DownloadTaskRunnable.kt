package com.fs.test1

import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.URL

class DownloadTaskRunnable(
    private val url: String,
    private val fileName: String,
    private val downloadPath: String

) : Runnable {

    private val bytes = ByteArray(1024 * 1024)



    override fun run() {

        try {
            val httpUrl = URL(url)
            val conn = httpUrl.openConnection()
            val contentLength = conn.getHeaderField("Content-Length") ?: ""
            if (contentLength.isEmpty()) {
                Log.d(TAG, "无效文件。。。。")
            } else {
                Log.d(TAG, "有效文件 -----> ContentLength --------  $contentLength")
            }
            val file = File(downloadPath + File.separatorChar + fileName)
            val fos = FileOutputStream(file)
            val inputStream = conn.getInputStream()
            var len = 0
            while (inputStream.read(bytes).also { len = it } != -1) {
                fos.write(bytes, 0, len)
//                Log.d(TAG, "当前下载进度 ---->  ${file.length()}")
            }
            fos.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}