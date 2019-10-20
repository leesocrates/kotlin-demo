package cn.example.myapplication.spi

import android.util.Log

class AService : IService{
    val TAG = AService::class.java.simpleName
    override fun doSomething() {
        Log.i(TAG, "AService doSomething")
    }

}
