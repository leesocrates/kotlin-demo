package cn.example.myapplication.activity

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.util.Log
import android.view.KeyEvent
import android.view.View
import androidx.annotation.RequiresApi
import cn.example.baselib.activity.BaseActivity
import cn.example.myapplication.MainActivity
import cn.example.myapplication.R
import cn.example.myapplication.h5.JavaMethods
import cn.example.myapplication.h5.WebPresenter
import kotlinx.android.synthetic.main.activity_webview.*
import java.io.IOException
import java.io.InputStream
import android.widget.LinearLayout
import cn.example.myapplication.h5.BaseWebViewActivity
import cn.example.myapplication.h5.WebViewManager
import com.tencent.smtt.export.external.interfaces.JsPromptResult
import com.tencent.smtt.export.external.interfaces.WebResourceRequest
import com.tencent.smtt.export.external.interfaces.WebResourceResponse
import com.tencent.smtt.sdk.ValueCallback
import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient


class WebViewActivity : BaseWebViewActivity(), WebPresenter {


    override fun showText(text: String) {
        web_tv_fromJS.text = text
    }

    private var mUploadMessageForAndroid5: ValueCallback<Array<Uri>>? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_webview
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun initOnCreate() {
        super.initOnCreate()
        val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        webViewContainer.addView(webView, layoutParams)
        initWebView()
        javaCallJs.setOnClickListener {
            //            webView.loadUrl("javascript:javatojscallback('我来自Java')");
            webView.evaluateJavascript("javascript:javatojscallback('我来自Java')") {
                web_tv.text = it
            }

        }

    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {
        setWebViewClient()
        setWebChromeClient()

        webView.addJavascriptInterface(JavaMethods(this), "android")
        webView.loadUrl("file:///android_asset/test.html")
    }



    //H5加载链接监听
    private fun setWebViewClient() {
        webView.webViewClient = object : WebViewClient() {
            @Override
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                var intent: Intent? = null
                //根据拦截的url来判断是否拦截跳转
                if (url.contains("mainactivity")) {
                    intent = Intent(this@WebViewActivity.applicationContext, MainActivity::class.java)
                } else if (url.contains("returnBackController")) {

                } else if (url.contains("reload")) {
                    webView.loadUrl("javascript:reload()")
                }else if(url.contains("zhangmen")){
                    val s = Uri.encode("https://10916-fat-7.qa.zmlearn.com/weike/list")
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("zhangmen://frame?url=$s&modulePath=activeWebPage&isHiddenNavBar=true"))
                    startActivity(intent)
                } else {
                    webView.loadUrl(url)
                }
                intent?.let {
                    startActivity(intent)
                    return true
                }
                return super.shouldOverrideUrlLoading(view, url)
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
            }

            //API21以下用shouldInterceptRequest(WebView view, String url)
            override fun shouldInterceptRequest(view: WebView?, url: String?): WebResourceResponse? {
                return super.shouldInterceptRequest(view, url)
            }

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            override fun shouldInterceptRequest(view: WebView?, request: WebResourceRequest?): WebResourceResponse? {
                if (request?.url.toString().contains("testImg")) { // 步骤1:判断拦截资源的条件，即判断url里的图片资源的文件名
                    var ins: InputStream? = null  // 步骤2:创建一个输入流
                    try {
                        ins = applicationContext.assets.open("images/ic_launcher.png")  // 步骤3:打开需要替换的资源(存放在assets文件夹里)
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                    //步骤4:替换资源
                    val response = WebResourceResponse("image/png",
                            "utf-8", ins)
                    return response
                }
                return super.shouldInterceptRequest(view, request)
            }

        }
    }

    //H5界面加载进度监听  文件选择器监听
    private fun setWebChromeClient() {
        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
            }

            // for Android >= 5.0
            override fun onShowFileChooser(webView: WebView?, filePathCallback: ValueCallback<Array<Uri>>?, fileChooserParams: FileChooserParams?): Boolean {
                onenFileChooseImpleForAndroid(filePathCallback)
                return super.onShowFileChooser(webView, filePathCallback, fileChooserParams)
            }

            override fun onJsPrompt(view: WebView?, url: String?, message: String?, defaultValue: String?, result: JsPromptResult?): Boolean {
                Log.i(TAG, "url: $url ;  message: $message")
                val uri = Uri.parse(message)
                if (uri.scheme == "js") {
                    if (uri.authority == "jstojava") {
                        val param3 = uri.getQueryParameter("arg3")
                        val param4 = uri.getQueryParameter("arg4")
                        web_tv_fromJS.text = "from JS : arg3=$param3 ; arg4=$param4"
                        result?.confirm("我来自onJsPrompt")
                        return true
                    }
                }
                return super.onJsPrompt(view, url, message, defaultValue, result)
            }

            // for Android < 5.0
//            override fun openFileChooser(uploadMsg: ValueCallback<Uri>, acceptType: String, capture: String){
//
//            }
        }
    }

    private fun onenFileChooseImpleForAndroid(filePathCallback: ValueCallback<Array<Uri>>?) {
        mUploadMessageForAndroid5 = filePathCallback
        var contentSelectionIntent = Intent(Intent.ACTION_GET_CONTENT)
        contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE)
        contentSelectionIntent.setType("image/*")

        var chooserIntent = Intent(Intent.ACTION_CHOOSER)
        chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent)
        chooserIntent.putExtra(Intent.EXTRA_TITLE, "Image Chooser")

        startActivityForResult(chooserIntent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        var result = intent?.data
        when (requestCode) {
            1 -> {
                if (null == mUploadMessageForAndroid5)
                    return;
                if (result != null) {
                    mUploadMessageForAndroid5?.onReceiveValue(Array<Uri>(1) { result })
                } else {
                    mUploadMessageForAndroid5?.onReceiveValue(emptyArray())
                }
                mUploadMessageForAndroid5 = null
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }



    override fun onDestroy() {
        super.onDestroy()
        webViewContainer.removeView(webView)
        webView.removeAllViews()
        webView.destroy()
//        WebViewManager.instance.recycleWebView(webView)
    }
}