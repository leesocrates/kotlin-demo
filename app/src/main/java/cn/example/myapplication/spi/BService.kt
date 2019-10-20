package cn.example.myapplication.spi

import android.util.Log

class BService : IService{
    val TAG = BService::class.java.simpleName
    override fun doSomething() {
        Log.i(TAG, "BService doSomething")
    }

}