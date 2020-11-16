package com.fs.test1

import com.fs.test1.util.getFileNameFromUrl
import org.junit.Test
import java.io.File
import java.net.URL
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.regex.Pattern

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun regex_test() {
        val pattern = Pattern.compile("(https?|ftp|file)://[-\\w+&@#/%=~|?!:,.;]+[-\\w+&@#/%=~|]")
//        val s = "https://r2---sn-bvn0o-tpil.gvt1.com/edgedl/android/studio/install/4.1.0.19/android-studio-ide-201.6858069-windows.exe?cms_redirect=yes&mh=kO&mip=218.70.86.22&mm=28&mn=sn-bvn0o-tpil&ms=nvh&mt=1604911515&mv=m&mvi=2&pl=17&shardbypass=yes"
        val s = "dddddddddd";
        val matcher = pattern.matcher(s)
        if (matcher.find())
            println(matcher.group())
        else println("没有匹配项")

    }

    @Test
    fun getCoreCount() {
        println(Runtime.getRuntime().availableProcessors())
        println(File.pathSeparatorChar)
        println(File.separatorChar)

        val s = "http://dl.2345.com/haozip/2345haozip_6.2.0.11032_setup.exe"
        val index = s.lastIndexOf(".")
        val nameIndex = s.lastIndexOf("/")
        println(s.substring(nameIndex +1, s.length))
    }

    @Test
    fun netTest() {
        val httpUrl = URL("http://dl.2345.com/haozip/2345haozip_6.2.0.11032_setup.exe")
        val conn = httpUrl.openConnection()
        val contentLength = conn.getHeaderField("Date") ?: ""
        println("ContentLength --------  $contentLength")

        if (contentLength.isEmpty()) {
            println("无效文件。。。。")
        } else {
            println("有效文件 -----> ContentLength --------  $contentLength")
        }
    }

    @Test
    fun myTest1() {

        val s = "https://cdn-file-ssl-android.ludashi.com/android/ludashi/ludashi_home.apk?t=1605063600"
        println(getFileNameFromUrl(s))
    }

    @Test
    fun myTest2() {
        val runnable1 = Runnable {
            Thread.sleep(1000)
            println("任务一异常")
            throw Error()
        }
        val runnable = Runnable {
            Thread.sleep(3000)
            println("任务二正常")
        }
        val threadPool = Executors.newFixedThreadPool(4)
        threadPool.submit (runnable)
        threadPool.submit(runnable1)
        throw Error()

    }

    @Test
    fun myTest3() {
        val scheduler = Executors.newScheduledThreadPool(2)
        scheduler.scheduleAtFixedRate({
            println("执行。。。。")
        }, 0, 1000, TimeUnit.SECONDS)
    }


    class Person(var age: Int) {

        fun age() {
            println(p.age)
        }
        lateinit var p: Person
        fun setPp(pp: Person) {
            this.p = pp
        }
    }

    @Test
    fun myTest4() {
        val p = Person(0)
        val p1 = Person(10)
        p.setPp(p1)
        p.age()
        p1.age = 20
        p.age()
    }

    @Test
    fun myTest5() {
        val list = arrayListOf<String>()
        list.add("1")
        list.add("2")
        list.add("3")
        list.add("4")
        list.add("5")

//        list.forEach {
//            if (it.toInt() == 3) list.remove(it)
//        }
        val iterator = list.iterator()
        while (iterator.hasNext()) {
            val s = iterator.next()
            println(s)
            if (s.toInt() == 3)
                iterator.remove()
        }
        list.forEach { println(it) }
    }

}