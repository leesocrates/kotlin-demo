package cn.example.myapplication.view

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.Button

class MyButton(context: Context, attrs: AttributeSet?, defStyleAttr: Int): Button(context, attrs, defStyleAttr) {
    private val TAG = this.javaClass.simpleName

    constructor(context: Context): this(context, null, 0)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, 0)

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        Log.i(TAG, "MyButton dispatchTouchEvent ${event?.action}")
        return super.dispatchTouchEvent(event)
//        return true
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.i(TAG, "MyButton onTouchEvent ${event?.action}")
        return super.onTouchEvent(event)
//        return false
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        Log.i(TAG, "MyButton onMeasure")
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        Log.i(TAG, "MyButton onLayout")
        super.onLayout(changed, left, top, right, bottom)
    }

    override fun onDraw(canvas: Canvas?) {
        Log.i(TAG, "MyButton onDraw")
        super.onDraw(canvas)
    }

    override fun draw(canvas: Canvas?) {
        Log.i(TAG, "MyButton draw()")
        super.draw(canvas)
    }

    override fun layout(l: Int, t: Int, r: Int, b: Int) {
        Log.i(TAG, "MyButton layout()")
        super.layout(l, t, r, b)
    }

}