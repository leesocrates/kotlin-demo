package cn.example.myapplication.h5

import android.content.Context
import android.util.Log
import android.webkit.WebView

class WebViewManager private constructor(){
    private val TAG = "WebViewManager"
    private val webViews = arrayOfNulls<WebView>(4)
    private var size = 0

    companion object{
        val instance: WebViewManager by lazy {
            WebViewManager()
        }
    }

    fun init(context: Context){
        val webView = WebView(context)
        recycleWebView(webView)
        Log.i(TAG, "WebViewManager initOnCreate finished")
    }

    fun getWebview(): WebView?{
        synchronized(webViews){
            if(size>0){
                return webViews[--size]
            }
            return null
        }
    }

    fun recycleWebView(webView: WebView?){
        webView?.let {
            if(size< webViews.size){
                webViews[size++] = webView
            }
        }
    }
}

// 静态内部类方式实现单例
//class PayServiceManager private constructor() {
////    companion object {
////        val instance =PayServiceManagerHolder.payServiceManager
////    }
////
////    private object PayServiceManagerHolder {
////        val payServiceManager = PayServiceManager()
////    }
////}