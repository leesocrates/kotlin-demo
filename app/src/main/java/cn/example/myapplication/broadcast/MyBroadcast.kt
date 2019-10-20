package cn.example.myapplication.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

private const val TAG = "MyBroadcast"

class MyBroadcast: BroadcastReceiver(){

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.i(TAG, "MyBroadcast onReceive ")
    }

}