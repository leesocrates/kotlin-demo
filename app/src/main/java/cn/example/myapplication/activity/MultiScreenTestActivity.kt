package cn.example.myapplication.activity

import android.util.Log
import android.view.View
import cn.example.baselib.activity.BaseActivity
import cn.example.baselib.util.ScreenUtil
import cn.example.myapplication.R
import kotlinx.android.synthetic.main.activity_multi_screen.*
import java.lang.Exception

/**
 *  author : Lee
 *  date : 2020/11/3
 *  description : 多屏幕适配方案测试
 */
class MultiScreenTestActivity :BaseActivity(){
    override fun getLayoutId(): Int {
        return R.layout.activity_multi_screen
    }

    override fun initOnCreate() {
        multiTv.text = "screen width : ${ScreenUtil.getScreenWidth(this)}\n screen height: ${ScreenUtil.getScreenHeight(this)} \n " +
                "screen density: ${ScreenUtil.getDensity(this)} \n screen densityDpi: ${ScreenUtil.getDensityDpi(this)}\n" +
                "screen xDpi: ${resources.displayMetrics.xdpi} \n screen yDpi: ${resources.displayMetrics.ydpi}"

        testView.postDelayed({ viewTv.text = "testView width: ${testView.width}  height: ${testView.height}" }, 1000)
    }

    override fun onPause() {
//        try {
//            Thread.sleep(2000)
//        } catch (e: Exception){
//
//        }
        Log.e(TAG, "onPause ${System.currentTimeMillis()}")
        super.onPause()
    }

    override fun onDestroy() {
        try {
            Thread.sleep(2000)
        } catch (e: Exception){

        }
        super.onDestroy()
        Log.e(TAG, "onDestroy ${System.currentTimeMillis()}")
    }
}