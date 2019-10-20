package cn.example.myapplication

import android.content.Context

import android.content.Intent
import android.os.Bundle
import cn.example.baselib.activity.BaseActivity
import cn.example.myapplication.activity.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initOnCreate() {
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
        openReactActivity.setOnClickListener {
//            startActivity(Intent(this, MyReactActivity::class.java))
        }
        openSurfaceActivity.setOnClickListener {
            startActivity(Intent(this, SurfaceActivity::class.java))
        }
//        var file = File(FileUtils.getAssetsCacheFile(this,"classes10.dex"))
//
//        HotFixUtils.loadFixedDex(this, file)
    }




    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
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
}
