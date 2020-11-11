package com.fs.test1

import com.fs.test1.util.getFileNameFromUrl
import com.fs.test1.util.getProcessors
import java.io.File
import java.net.URLConnection
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadFactory
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class Downloader {

    private lateinit var threadPool: ThreadPoolExecutor

    init {

        val corePoolSize = if (getProcessors() <= 8)
            getProcessors() else 8
        val maxPoolSize = getProcessors()

        threadPool = ThreadPoolExecutor(
            corePoolSize,
            maxPoolSize,
            0L,
            TimeUnit.MILLISECONDS,
            LinkedBlockingQueue<Runnable>(),
            ThreadFactory { Thread(it) })

    }

    public fun download(
        url: String,
        invalidBlock: () -> Unit,
        beginBlock: () -> Unit,
        updateBlock: () -> Unit
    ) {

        val fileName = getFileNameFromUrl(url)
        val path = DEFAULT_DOWNLOAD_PATH + File.separatorChar + DEFAULT_DOWNLOAD_DIR
        val  downloadDir = File(path)
        if (!downloadDir.exists()) downloadDir.mkdir()
        val task = DownloadTaskRunnable(url, fileName, path)
        threadPool.submit(task)

/*        try {
            val httpUrl = URL(url)
            val conn = httpUrl.openConnection()
            val contentLength = conn.getHeaderField("Content-Length") ?: ""
            Log.d(TAG, "ContentLength --------  $contentLength")
            if (contentLength.isEmpty()) {
                Log.d(TAG, "")
                invalidBlock.invoke()
            } else {
                beginBlock.invoke()
                realDownload(url, contentLength, conn, updateBlock)
                Log.d(TAG, "  ")
            }
        } catch (e: IOException) {
            Log.d(TAG, "download --- 出错了...  ")
            e.printStackTrace()
        }*/
    }

    private fun realDownload(
        url: String,
        contentLength: String,
        conn: URLConnection,
        updateBlock: () -> Unit
    ) {
/*        val len = contentLength.toLong()
        val fileName = url.substring(url.lastIndexOf("/") + 1, url.length)
        val path = DEFAULT_DOWNLOAD_PATH + File.pathSeparatorChar + DEFAULT_DOWNLOAD_DIR
        val task = DownloadTaskRunnable(url, len, fileName, path, conn, updateBlock)
        threadPool.submit(task)*/

    }


}