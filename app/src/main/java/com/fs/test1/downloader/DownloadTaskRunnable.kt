package com.fs.test1.downloader

import android.util.Log
import com.fs.test1.TAG
import com.fs.test1.exception.ValidDownloadFileException
import com.fs.test1.pojo.DownloadHistoryPoJo
import java.io.*
import java.net.*

class DownloadTaskRunnable : Runnable {

    private var url: String
    var fileName: String
    var downloadPath: String
    var config: DownloadConfig? = null

    constructor(url: String, fileName: String, downloadPath: String) {
        this.url = url
        this.fileName = fileName
        this.downloadPath = downloadPath
    }

    constructor(url: String, fileName: String, config: DownloadConfig) {
        this.url = url
        this.fileName = fileName
        this.config = config
        this.downloadPath = config.downloadPath
    }

    var downloadedFileSize = 0L
    var downloadedPercent = 0
    var totalFileSize = 0L
    var downloadProgressListener: ((downloadedPercent: Int, downloadedSize: Long) -> Unit)? = null
    override fun run() {

        try {
            val httpUrl = URL(url)

            val conn = if (config == null) httpUrl.openConnection()
            else {
                if (config!!.proxy == null) httpUrl.openConnection()
                else {
                    val proxy = Proxy(Proxy.Type.HTTP, InetSocketAddress(config!!.proxy!!.hostName, config!!.proxy!!.portNum))
                    httpUrl.openConnection(proxy)
                }
            }

            conn.connectTimeout = 5000
            conn.readTimeout = 10000
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

            val fos = FileOutputStream(file)
            val inputStream = conn.getInputStream()
            var len = 0
            val bytes = ByteArray(1024 * 1024)
            while (inputStream.read(bytes).also { len = it } != -1) {
                fos.write(bytes, 0, len)
                downloadedFileSize = file.length()
                downloadedPercent = (downloadedFileSize * 100 / totalFileSize).toInt()
                downloadProgressListener?.invoke(downloadedPercent, downloadedFileSize)
                if (downloadedFileSize == totalFileSize) {
                    Log.d(TAG, "下载完成....")
                    TaskManager.addDownloadHistory(
                        DownloadHistoryPoJo(
                            fileName,
                            ((totalFileSize / 1024 / 1024).toString()),
                            downloadPath + File.separatorChar + fileName
                        )
                    )
                    Downloader.getInstance().downloadFinishListener.invoke(
                        fileName,
                        totalFileSize.toString(),
                        downloadPath + File.separatorChar + fileName
                    )
                }
            }
            fos.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}