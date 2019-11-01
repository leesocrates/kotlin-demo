package cn.example.myapplication.h5

import android.content.res.Configuration
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
//import android.webkit.WebView
//import android.webkit.WebViewClient
import cn.example.myapplication.R
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient
import kotlinx.android.synthetic.main.activity_video_fullscreen.*

/**
 *  author : Lee
 *  date : 2019/10/23
 *  description :
 */
class VideoFullScreenActivity : BaseWebViewActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_video_fullscreen
    }

    override fun initOnCreate() {
        super.initOnCreate()
        val videoWebChromeClient = VideoWebChromeClient(totalLayout)
        videoWebChromeClient.setOnToggledFullscreen(object : VideoWebChromeClient.ToggledFullscreenCallback {
            override fun toggledFullscreen(fullscreen: Boolean) {
                Log.i("VideoFullScreenTest", "toggledFullscreen：$fullscreen")
                if (fullscreen) {
                    val attrs = window.attributes
                    attrs.flags = attrs.flags or WindowManager.LayoutParams.FLAG_FULLSCREEN
                    attrs.flags = attrs.flags or WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
//                    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
                    window.attributes = attrs
                    if (android.os.Build.VERSION.SDK_INT >= 14) {

                        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LOW_PROFILE
                    }
                } else {
                    val attrs = window.attributes
                    attrs.flags = attrs.flags and WindowManager.LayoutParams.FLAG_FULLSCREEN.inv()
                    attrs.flags = attrs.flags and WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON.inv()
//                    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                    window.attributes = attrs
                    if (android.os.Build.VERSION.SDK_INT >= 14) {

                        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
                    }
                }
            }
        })
        webView.webChromeClient = videoWebChromeClient
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                Log.i("VideoFullScreenTest", "shouldOverrideUrlLoading url is $url")
                if (url!=null && (url.startsWith("http") || url.startsWith("https"))) {
                    webView.loadUrl(url)
                    return true
                }
                return super.shouldOverrideUrlLoading(view, url)
            }
        }

        val mWebView = android.webkit.WebView(this)
        mWebView.settings.javaScriptEnabled = true
        totalLayout.addView(mWebView, 0)
        mWebView.loadUrl("http://192.168.62.164:3000/")

//        totalLayout.addView(webView, 0)
//        webView.loadUrl("https://www.baidu.com")
//        webView.loadUrl("file:///android_asset/test.html")
//        webView.loadUrl("http://192.168.62.164:3000/")
    }

    override fun onConfigurationChanged(config: Configuration) {
        super.onConfigurationChanged(config)
        when (config.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                window.clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN)
                window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            }
            Configuration.ORIENTATION_PORTRAIT -> {
                window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
                window.addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        val parent = webView.parent
        if(parent!=null && parent is ViewGroup){
            parent.removeView(webView)
        }
        webView.removeAllViews()
//        webView.destroy()
//        WebViewManager.instance.recycleWebView(webView)
    }
}