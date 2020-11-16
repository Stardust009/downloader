package com.fs.test1.downloader

import com.fs.test1.DEFAULT_DOWNLOAD_DIR
import com.fs.test1.DEFAULT_DOWNLOAD_PATH
import com.fs.test1.util.getFileNameFromUrl
import com.fs.test1.util.getProcessors
import java.io.File
import java.net.URLConnection
import java.util.concurrent.*

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
    lateinit var downloadFinishListener: (name: String, size: String, path: String) -> Unit

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

    }


    fun download(url: String, config: DownloadConfig? = null) {

        var fileName = getFileNameFromUrl(url)

        val path = config?.downloadPath
            ?: DEFAULT_DOWNLOAD_PATH + File.separatorChar + DEFAULT_DOWNLOAD_DIR
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
        val task = if (config == null) DownloadTaskRunnable(url, fileName, path)
        else DownloadTaskRunnable(url, fileName, config)

        threadPool.submit(task)

//        val t = Thread(task)
//        t.start()
    }


}