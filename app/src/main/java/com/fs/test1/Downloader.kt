package com.fs.test1

import android.os.Environment
import com.fs.test1.util.getFileNameFromUrl
import com.fs.test1.util.getProcessors
import java.io.File
import java.net.URLConnection
import java.util.concurrent.*
import kotlin.concurrent.thread

class Downloader {

    companion object {

        @Volatile
        private var downloader: Downloader? = null

/*
        val downloaderInstance: Downloader?
            get() {
                downloader ?: synchronized(this) {
                    downloader ?: Downloader().also { downloader = it }
                }
               return downloader
            }
*/

        fun getInstance(): Downloader = downloader ?: synchronized(this) {
            downloader ?: Downloader().also {
                downloader = it
            }
        }
    }

    private var threadPool: ThreadPoolExecutor
    private lateinit var updateProgressListener: (downloadedSize: Int) -> Unit

    init {

        val corePoolSize = if (getProcessors() <= 8)
            getProcessors() else 8
        val maxPoolSize = getProcessors()

        threadPool = ThreadPoolExecutor(
            corePoolSize,
            maxPoolSize,
            60L,
            TimeUnit.SECONDS,
            LinkedBlockingQueue(),
            ThreadFactory { Thread(it) })

//        threadPool = Executors.newCachedThreadPool { Thread(it) } as ThreadPoolExecutor

    }

    public fun download(
        url: String,
        invalidBlock: () -> Unit,
        beginBlock: () -> Unit,
        updateBlock: () -> Unit
    ) {

        var fileName = getFileNameFromUrl(url)

        val path = DEFAULT_DOWNLOAD_PATH + File.separatorChar + DEFAULT_DOWNLOAD_DIR
        val downloadDir = File(path)
        if (!downloadDir.exists()) downloadDir.mkdir()
        var f = File(path + File.separatorChar + fileName)
        var i = 1
        val s = fileName.substring(0, fileName.indexOf("."))
        val suffix = fileName.substring(fileName.indexOf("."), fileName.length)
        while (f.exists()) {
            fileName = "$s($i)$suffix"
            f = File(path + File.separatorChar + fileName)
            i++
        }
        val task = DownloadTaskRunnable(url, fileName, path)
//        threadPool.submit(task)

        val t = Thread(task)
        t.start()
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