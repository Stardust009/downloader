package com.fs.test1.util

import java.io.File
import java.util.regex.Pattern

fun getProcessors() = Runtime.getRuntime().availableProcessors()

fun checkUrl(url: String): Boolean {

    val pattern = Pattern.compile("(https?|ftp|file)://[-\\w+&@#/%=~|?!:,.;]+[-\\w+&@#/%=~|]")
    val matcher = pattern.matcher(url)
    return matcher.find()
}

fun getFileNameFromUrl(url: String): String {
    val lastPathSeparatorIndex = url.lastIndexOf("/")
    val urlSeparatorIndex = url.indexOf("?")
    return if (urlSeparatorIndex == -1)
        url.substring(lastPathSeparatorIndex + 1, url.length)
    else url.substring(lastPathSeparatorIndex + 1, urlSeparatorIndex)

}