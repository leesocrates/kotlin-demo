package cn.example.myapplication.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

open class BaseView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : View(context, attrs, defStyleAttr) {
    protected val TAG: String = this.javaClass.simpleName
    protected var mWidth = 320
    protected var mHeight = 480
    protected var mCanvas: Canvas? = null
    protected var paint: Paint = Paint()
    protected var bitmap: Bitmap? = null

    constructor(context: Context) : this(context, null, 0)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w
        mHeight = h
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        releaseMemory()
    }

    private fun releaseMemory() {
        recycleBitmap(bitmap)
    }

    private fun recycleBitmap(vararg bitmaps: Bitmap?) {
        for (bitmap in bitmaps){
            if(bitmap!==null && !bitmap.isRecycled){
                bitmap.recycle()
            }
        }
    }



}