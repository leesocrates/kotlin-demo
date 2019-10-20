package cn.example.myapplication.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.LinearLayout

class ParentLinearLayout(context: Context, attrs: AttributeSet?, defStyleAttr: Int): LinearLayout(context, attrs, defStyleAttr) {
    private val TAG = this.javaClass.simpleName

    constructor(context: Context): this(context, null, 0)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, 0)

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.i(TAG, "ParentLinearLayout dispatchTouchEvent ${ev?.action}")
        return super.dispatchTouchEvent(ev)
//        return true
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        Log.i(TAG, "ParentLinearLayout onInterceptTouchEvent ${ev?.action}")
//        if(ev?.action == MotionEvent.ACTION_MOVE){
//            return true
//        }
        return super.onInterceptTouchEvent(ev)
//        return true
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.i(TAG, "ParentLinearLayout onTouchEvent ${event?.action}")
        return super.onTouchEvent(event)
//        return true
    }

}