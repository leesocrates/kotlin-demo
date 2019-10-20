package cn.example.myapplication.activity

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import cn.example.baselib.activity.BaseActivity
import cn.example.myapplication.R
import cn.example.myapplication.aidl.IMyService
import cn.example.myapplication.service.LocalService
import cn.example.myapplication.service.RemoteService
import kotlinx.android.synthetic.main.activity_service_test.*
import java.util.HashMap

class ServiceTestActivity : BaseActivity(){

    private val serviceConnection = object: ServiceConnection{
        override fun onServiceDisconnected(name: ComponentName?) {

        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val myBinder = service as LocalService.MyBinder
            val result = myBinder.add(1, 3)
            Log.i(TAG, "myBinder.add(1, 3) result is $result")
            serviceTv.text = "LocalService onServiceConnected"
        }

    }

    private val remoteServiceConnection = object: ServiceConnection{
        override fun onServiceDisconnected(name: ComponentName?) {

        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.i(TAG, "service IBinder is $service")
            val myService = IMyService.Stub.asInterface(service)
            Log.i(TAG, "remoteServiceConnection myService.add(1, 2) result is ${myService.add(1,2)}")
            serviceTv.text = "remoteServiceConnection"
        }

    }


    override fun getLayoutId(): Int {
        return R.layout.activity_service_test
    }

    override fun initOnCreate() {
        startServiceBtn.setOnClickListener {
            startService(Intent(this, RemoteService::class.java))
        }

        bindServiceBtn.setOnClickListener {
            bindService(Intent(this@ServiceTestActivity, RemoteService::class.java), remoteServiceConnection, 0)
        }

        unBindServiceBtn.setOnClickListener {
            unbindService(remoteServiceConnection)
        }

    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}