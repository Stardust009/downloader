package com.fs.test1.util

import java.util.regex.Pattern

fun checkUrl(url: String):Boolean {

    val pattern = Pattern.compile("(https?|ftp|file)://[-\\w+&@#/%=~|?!:,.;]+[-\\w+&@#/%=~|]")
    val s = "https://r2---sn-bvn0o-tpil.gvt1.com/edgedl/android/studio/install/4.1.0.19/android-studio-ide-201.6858069-windows.exe?cms_redirect=yes&mh=kO&mip=218.70.86.22&mm=28&mn=sn-bvn0o-tpil&ms=nvh&mt=1604911515&mv=m&mvi=2&pl=17&shardbypass=yes"
    val matcher = pattern.matcher(s)
    return matcher.find()
}