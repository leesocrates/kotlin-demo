package cn.example.myapplication.activity

import android.util.Log
import android.view.MotionEvent
import android.widget.LinearLayout
import cn.example.baselib.activity.BaseActivity
import cn.example.myapplication.R
import kotlinx.android.synthetic.main.activity_view_event.*

/**
 * view事件分发流程测试类
 */
class ViewEventActivity: BaseActivity(){
    override fun getLayoutId(): Int {
        return R.layout.activity_view_event
    }

    override fun initOnCreate() {
        myBtn.setOnClickListener{
            Log.i(TAG, "myBtn is click ${it.layoutParams.width}")
//            val layoutParams = it.layoutParams
//            layoutParams.width = 1000
//            it.layoutParams = layoutParams
//            it.layoutParams.width = 1000
            myBtn.requestLayout()
            it.invalidate()
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.i(TAG, "ViewEventActivity onTouchEvent ${event?.action}")
        return super.onTouchEvent(event)
    }

    override fun onUserInteraction() {
        Log.i(TAG, "ViewEventActivity onUserInteraction")
        super.onUserInteraction()
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.i(TAG, "ViewEventActivity dispatchTouchEvent action is  ${ev?.action}")
        return super.dispatchTouchEvent(ev)
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "window is ${this.window}")
    }

}