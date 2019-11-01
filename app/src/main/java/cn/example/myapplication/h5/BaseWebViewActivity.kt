package cn.example.myapplication.h5

import android.view.KeyEvent
import android.view.View
//import android.webkit.WebSettings
//import android.webkit.WebView
import cn.example.baselib.activity.BaseActivity
import com.tencent.smtt.sdk.WebSettings
import com.tencent.smtt.sdk.WebView

abstract class BaseWebViewActivity: BaseActivity() {
    lateinit var webView: WebView

    override fun initOnCreate() {
//        webView = WebViewManager.instance.getWebview() ?: WebView(applicationContext)
        webView = WebView(this)
        initBaseWebView()
    }

    private fun initBaseWebView(){
        val mWebSettings = webView.settings
        mWebSettings.javaScriptEnabled = true //允许加载javaScript  // 只需设置支持JS就自动打开IndexedDB存储机制 // Android 在4.4开始加入对 IndexedDB 的支持，只需打开允许 JS 执行的开关就好了。
        mWebSettings.setSupportZoom(true)      //是否允许缩放
        mWebSettings.useWideViewPort = true   //设置加载进来的页面自适应手机屏幕
        mWebSettings.loadWithOverviewMode = true
        mWebSettings.cacheMode = WebSettings.LOAD_DEFAULT
        mWebSettings.setRenderPriority(WebSettings.RenderPriority.HIGH)
        mWebSettings.defaultFontSize = 20 * 3

        setBackListener()

        setAppCache(mWebSettings)
        mWebSettings.domStorageEnabled = true
        mWebSettings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
    }

    private fun setAppCache(settings: WebSettings) {

        val cacheDirPath = applicationContext.filesDir.absolutePath + "cache/"
        settings.setAppCachePath(cacheDirPath)
        // 1. 设置缓存路径

        settings.setAppCacheMaxSize(20 * 1024 * 1024)
        // 2. 设置缓存大小

        settings.setAppCacheEnabled(true)
        // 3. 开启Application Cache存储机制

        // 每个 Application 只调用一次 WebSettings.setAppCachePath() 和 WebSettings.setAppCacheMaxSize()

    }

    private fun setBackListener() {
        webView.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
                    webView.goBack()
                    return true
                }
                return false
            }

        })
    }
}