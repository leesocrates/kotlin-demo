package cn.example.myapplication.h5

import android.os.Handler
import android.os.Looper
import android.webkit.JavascriptInterface

class JavaMethods {
    private var webPresenter: WebPresenter
    private var uiHandler: Handler

    constructor(mainActivity: WebPresenter){
        this.webPresenter= mainActivity
        uiHandler = Handler(Looper.getMainLooper())
    }


    @JavascriptInterface
    fun JsToJavaInterface( param: String) {
        uiHandler.post{
            webPresenter.showText("from JavaInterface: $param")
        }
    }
}