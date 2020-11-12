package com.fs.test1

import android.util.Log
import com.fs.test1.exception.ValidDownloadFileException
import java.io.*
import java.net.URL

class DownloadTaskRunnable(
    val url: String,
    val fileName: String,
    val downloadPath: String

) : Runnable {

    var downloadedFileSize = 0L
    var downloadedPercent = 0
    var totalFileSize = 0L

    var downloadProgressListener: ((downloadedPercent: Int, downloadedSize: Long) -> Unit)? = null
    override fun run() {

        try {
            val httpUrl = URL(url)
            val conn = httpUrl.openConnection()
            val contentLength = conn.getHeaderField("Content-Length") ?: ""
            if (contentLength.isEmpty()) {
                Log.d(TAG, "无效文件。。。。抛出异常 结束线程")
                throw ValidDownloadFileException()
            } else {
                Log.d(TAG, "有效文件 -----> ContentLength --------  $contentLength")
                totalFileSize = contentLength.toLong()
                TaskManager.addDownloadingTask(this)
            }
            val file = File(downloadPath + File.separatorChar + fileName)
            Log.d(TAG, file.absolutePath)
            val fos = FileOutputStream(file)
            val inputStream = conn.getInputStream()
            var len = 0
            val bytes = ByteArray(1024 * 1024)
            while (inputStream.read(bytes).also { len = it } != -1) {
                fos.write(bytes, 0, len)
                downloadedFileSize = file.length()
                downloadedPercent = (downloadedFileSize * 100 / totalFileSize).toInt()
                Log.d(TAG, fileName + " :  当前下载进度 ---->  ${file.length()}")
                downloadProgressListener?.invoke(downloadedPercent, downloadedFileSize)
            }
            fos.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}