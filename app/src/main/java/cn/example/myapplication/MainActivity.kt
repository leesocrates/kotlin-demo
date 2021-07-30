package cn.example.myapplication

import android.content.ComponentName
import android.content.Context

import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.PackageManager.DONT_KILL_APP
import android.os.Bundle
import android.util.Log
import android.view.View
import cn.example.baselib.activity.BaseActivity
import cn.example.businessone.activity.BusinessOneActivity
import cn.example.myapplication.activity.*
import cn.example.myapplication.activity.tasktest.TaskOneActivity
import cn.example.myapplication.fragment.CustomDialogFragment
import cn.example.myapplication.h5.VideoFullScreenActivity
import cn.example.myapplication.monitor.FrameUtils
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : BaseActivity() {

    var dialog: CustomDialogFragment? = null
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initOnCreate() {
        openBusinessOne.setOnClickListener {
            startActivity(Intent(this, BusinessOneActivity::class.java))
        }
        openFragmentActivity.setOnClickListener {
            startActivity(Intent(this, FragmentActivity::class.java))
        }
        openWebViewActivity.setOnClickListener {
            startActivity(Intent(this, WebViewActivity::class.java))
        }
        openHandlerActivity.setOnClickListener {
            startActivity(Intent(this, HandlerActivity::class.java))
        }

        openEventActivity.setOnClickListener {
            startActivity(Intent(this, ViewEventActivity::class.java))
        }
        openServiceActivity.setOnClickListener {
            startActivity(Intent(this, ServiceTestActivity::class.java))
        }
        openAnimationActivity.setOnClickListener {
            startActivity(Intent(this, AnimatorActivity::class.java))
        }
        openCustomViewActivity.setOnClickListener {
            startActivity(Intent(this, CustomViewActivity::class.java))
        }
        openRetrofitActivity.setOnClickListener {
            startActivity(Intent(this, RetrofitActivity::class.java))
        }
        openSurfaceActivity.setOnClickListener {
            startActivity(Intent(this, SurfaceActivity::class.java))
        }
        openVideoFullScreenActivity.setOnClickListener {
            startActivity(Intent(this, VideoFullScreenActivity::class.java))
        }

        changeAlias.setOnClickListener {
            setLaunchActivity()
        }

        multiScreenActivity.setOnClickListener {
            startActivity(Intent(this, MultiScreenTestActivity::class.java))
        }

        taskActivity.setOnClickListener {
            startActivity(Intent(this, TaskOneActivity::class.java))
        }

        audioTestActivity.setOnClickListener {
            startActivity(Intent(this, AudioTestActivity::class.java))
        }
//        var file = File(FileUtils.getAssetsCacheFile(this,"classes10.dex"))
//        HotFixUtils.loadFixedDex(this, file)

//        FrameUtils().startMonitor() // 在每一帧执行时打印log


    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "onResume ${System.currentTimeMillis()}")
    }

    override fun onPause() {
        Log.e(TAG, "onPause ${System.currentTimeMillis()}")
        super.onPause()
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onPostResume() {
        super.onPostResume()
    }

    // 参考 https://juejin.im/post/5c36f2226fb9a049b7809170
    fun setLaunchActivity() {
        val packageManager = packageManager
        packageManager.setComponentEnabledSetting(ComponentName(this, packageName +
                ".NewActivity1"), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, DONT_KILL_APP)
        packageManager.setComponentEnabledSetting(ComponentName(this, packageName +
                ".NewActivity2"), PackageManager.COMPONENT_ENABLED_STATE_ENABLED, DONT_KILL_APP)
        packageManager.setComponentEnabledSetting(ComponentName(this, packageName +
                ".MainActivity"), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, DONT_KILL_APP)
    }

}
