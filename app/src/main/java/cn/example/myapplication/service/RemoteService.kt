package cn.example.myapplication.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import cn.example.myapplication.aidl.IMyService

class RemoteService: Service() {
    private val mBinder = object: IMyService.Stub(){
        override fun add(a: Int, b: Int): Int {
            return a+b
        }

        override fun basicTypes(anInt: Int, aLong: Long, aBoolean: Boolean, aFloat: Float, aDouble: Double, aString: String?) {
            Log.i("RemoteService", "basicTypes: $anInt")
        }

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i("RemoteService", "RemoteService onStartCommand()")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onCreate() {
        Log.i("RemoteService", "RemoteService onCreate()")
        super.onCreate()
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.i("RemoteService", "RemoteService onBind()")
        return mBinder
    }

    override fun onRebind(intent: Intent?) {
        Log.i("RemoteService", "RemoteService onRebind()")
        super.onRebind(intent)
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.i("RemoteService", "RemoteService onUnbind()")
        return super.onUnbind(intent)
    }
}