package cn.example.myapplication.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class LocalService : Service() {
    private lateinit var myBinder: MyBinder
    private val TAG = this.javaClass.simpleName
    private var i = 0

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "LocalService onCreate")
//        Thread.sleep(22*1000)
        myBinder = MyBinder()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(TAG, "LocalService onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.i(TAG, "LocalService onBind")
        return myBinder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.i(TAG, "LocalService onUnbind")
        return true
    }

    override fun onRebind(intent: Intent?) {
        Log.i(TAG, "LocalService onRebind")
        super.onRebind(intent)
    }

    override fun onDestroy() {
        Log.i(TAG, "LocalService onDestroy")
        super.onDestroy()
    }

    inner class MyBinder : Binder() {
        fun getService(): LocalService {
            return this@LocalService
        }

        fun add(i: Int, j: Int): Int {
            return i + j
        }
    }
}