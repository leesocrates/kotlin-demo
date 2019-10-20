package cn.example.myapplication.activity

import android.content.Intent
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import cn.example.baselib.activity.BaseActivity
import cn.example.myapplication.R

class BroadcastActivity: BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_service_test
    }

    override fun initOnCreate() {
        LocalBroadcastManager.getInstance(this).sendBroadcast(Intent())
    }
}