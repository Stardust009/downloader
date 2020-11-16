package com.fs.test1.downloader

import com.fs.test1.DEFAULT_DOWNLOAD_DIR
import com.fs.test1.DEFAULT_DOWNLOAD_PATH
import com.fs.test1.pojo.DownloadProxyPoJo
import java.io.File
import java.io.Serializable

class DownloadConfig : Serializable {


    var proxy: DownloadProxyPoJo? = null
        private set
    var downloadPath = DEFAULT_DOWNLOAD_PATH + File.separatorChar + DEFAULT_DOWNLOAD_DIR
        private set

    class Builder {

        private var downloadConfig = DownloadConfig()

        fun downloadPath(path: String): Builder {
            downloadConfig.downloadPath = path
            return this
        }

        fun proxy(proxy: DownloadProxyPoJo): Builder {
            downloadConfig.proxy = proxy
            return this
        }

        fun build(): DownloadConfig {
            return downloadConfig
        }
    }
}