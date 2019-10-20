package cn.example.myapplication

import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import cn.example.myapplication.h5.WebViewManager
import cn.example.myapplication.spi.IService
import java.util.*
import com.yz.ui.fragmentdemo.MyEventBusIndex
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus


class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "MyApplication onCreate")
        val loader = ServiceLoader.load(IService::class.java)
        val iterator = loader.iterator()
        while (iterator.hasNext()){
            iterator.next().doSomething()
        }
        EventBus.builder().addIndex(MyEventBusIndex()).installDefaultEventBus()
//        GlobalScope.launch(Dispatchers.Default){
//            Looper.prepare()
//
//            Looper.loop()
//        }
//        Log.i(TAG, "MyApplication onCreate finished")
        WebViewManager.instance.init(applicationContext)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }

    override fun registerActivityLifecycleCallbacks(callback: ActivityLifecycleCallbacks?) {
        super.registerActivityLifecycleCallbacks(callback)
    }

    companion object{
        private const val TAG = "MyApplication"
    }
}