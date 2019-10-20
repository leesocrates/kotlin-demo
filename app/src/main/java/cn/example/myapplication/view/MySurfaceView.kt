package cn.example.myapplication.view

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView

class MySurfaceView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : SurfaceView(context, attrs, defStyleAttr), SurfaceHolder.Callback, Runnable{
    private var mCanvas: Canvas? = null
    private var mIsRunning: Boolean = false
    private val TAG = "PencilView"

    constructor(context: Context): this(context, null, 0)
    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, 0)

    init {
        holder.addCallback(this)
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        Log.i(TAG, "surfaceCreated: ")
        mIsRunning = true
        Thread(this).start()
    }


    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        Log.i(TAG, "surfaceChanged: ")
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        Log.i(TAG, "surfaceDestroyed: ")
        mIsRunning = false
    }

    override fun run() {
        val start = System.currentTimeMillis()

        while (mIsRunning) {
            draw()
        }
    }

    private fun draw() {
        mCanvas = holder.lockCanvas()
        if (mCanvas != null) {
            try {
                //使用获得的Canvas做具体的绘制
                mCanvas?.drawRGB(222, 0, 0)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                holder.unlockCanvasAndPost(mCanvas)
            }
        }
    }

}