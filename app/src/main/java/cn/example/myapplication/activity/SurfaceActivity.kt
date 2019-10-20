package cn.example.myapplication.activity

import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.webkit.WebChromeClient
import android.widget.FrameLayout
import cn.example.myapplication.R
import cn.example.myapplication.h5.BaseWebViewActivity
import cn.example.myapplication.view.CustomSurfaceView
import kotlinx.android.synthetic.main.activity_surface.*

class SurfaceActivity: BaseWebViewActivity() {
    lateinit var surfaceView: CustomSurfaceView
    override fun getLayoutId(): Int {
        return R.layout.activity_surface
    }

    override fun initOnCreate() {
        super.initOnCreate()
        containerSurface.addView(webView, 0)
        initWebView()
        webView.loadUrl("file:///android_asset/test.html")
        initSurfaceView()
    }

    private fun initSurfaceView(){
        surfaceView = CustomSurfaceView(this)
        val layoutParams = FrameLayout.LayoutParams(300, 300)
        surfaceView.layoutParams = layoutParams
        surfaceView.setZOrderOnTop(true)
        surfaceView.setParentView(containerSurface)
        containerSurface.addView(surfaceView)
    }

    fun initWebView(){
        webView.webChromeClient = object: WebChromeClient(){
            override fun onShowCustomView(view: View?, callback: CustomViewCallback?) {
                super.onShowCustomView(view, callback)
                Log.i("webview", "onShowCustomView")
            }
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        surfaceView.processTouchEvent(ev)
        return super.dispatchTouchEvent(ev)
    }
}