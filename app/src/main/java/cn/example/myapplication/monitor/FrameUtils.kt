package cn.example.myapplication.monitor

import android.util.Log
import android.view.Choreographer

/**
 *  author : Lee
 *  date : 2021/3/18
 *  description :
 */
class FrameUtils {
    private var i=1;

    fun startMonitor(){
        val choreographer: Choreographer = Choreographer.getInstance()
        setFrameCallback(choreographer)
    }

    private fun setFrameCallback(choreographer: Choreographer){
        choreographer.postFrameCallback {
            setFrameCallback(choreographer)
//            Log.e("FrameUtils", "frame is ${i++} time is ${System.currentTimeMillis()}")
        }
    }
}