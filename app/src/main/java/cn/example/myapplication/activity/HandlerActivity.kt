package cn.example.myapplication.activity

import android.content.Context
import android.os.Handler
import android.os.Message
import android.util.Log
import cn.example.baselib.activity.BaseActivity
import cn.example.myapplication.MainActivity
import cn.example.myapplication.R
import java.lang.ref.WeakReference

class HandlerActivity : BaseActivity(){

    private lateinit var handler: MyHandler
    private lateinit var handler2: MyHandler

    override fun getLayoutId(): Int {
        return R.layout.activity_handler
    }

    override fun initOnCreate() {
        handler = MyHandler(this)
        handler2 = MyHandler(this)
        handler.postDelayed(object : Runnable{
            override fun run() {
                Log.i(TAG, "handler run")
                Thread.sleep(3000)
            }

        }, 0)
        handler2.postDelayed(object : Runnable{
            override fun run() {
                Log.i(TAG, "handler2 run")
            }

        }, 0)
        Log.i(TAG, "inflateObj ${getSystemService(Context.ACTIVITY_SERVICE)}")
    }

    override fun onDestroy() {
        handler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }

    private class MyHandler(mainActivity: HandlerActivity) : Handler() {
        private val mActivity: WeakReference<HandlerActivity> = WeakReference(mainActivity)
        private var name: String? = null

        constructor(mainActivity: HandlerActivity, name: String): this(mainActivity){
            this.name = name
        }

        override fun dispatchMessage(msg: Message?) {
            if (mActivity.get() == null) {
                return
            }
            val activity = mActivity.get()
            when (msg?.what) {
                0 -> {
                    Log.i("MyHandler", "$name get msg what is 0")
                    super.dispatchMessage(msg)
                }
                else -> {
                    Log.i("MyHandler", "$name get msg what is else")
                    super.dispatchMessage(msg)
                }
            }
        }

    }
}