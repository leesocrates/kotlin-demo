package com.zm.jvmtest.thread

import android.os.Bundle
import android.os.Process
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.zm.jvmtest.R
import kotlinx.android.synthetic.main.activity_thread_test.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.net.Socket

/**
 *  author : Lee
 *  date : 2020/8/19
 *  description :
 */
class ThreadTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread_test)
        openMultiIoThread.setOnClickListener {
            for (i in 0..500) {
                Thread(Runnable {
                    Socket("192.168.60.145", 8090).getInputStream()
                }).start()
            }
        }
        openMultiCpuThread.setOnClickListener {
            printSysInfo("/proc/meminfo")
            printSysInfo("/proc/" + Process.myPid() + "/status")
            for (i in 0..1000) {
                Thread(ThreadGroup("cpuGroup"),
                        Runnable {
                            try {
                                Thread.sleep(20000)
                            } catch (e: Exception) {
                            }
                        }, "Thread$i", 1024*1024*4).start()
            }
            printSysInfo("/proc/meminfo")
            printSysInfo("/proc/" + Process.myPid() + "/status")
        }
    }

    private fun printSysInfo(path: String) {
        try {
            val fileInputStream = FileInputStream(File(path))
            val result = ByteArrayOutputStream()
            val buffer = ByteArray(1024)
            var length: Int
            while (fileInputStream.read(buffer).also { length = it } != -1) {
                result.write(buffer, 0, length)
            }
            Log.e("ThreadTest", """$path out is : ${result.toString("UTF-8")}""")
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
    }
}