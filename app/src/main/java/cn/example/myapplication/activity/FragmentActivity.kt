package cn.example.myapplication.activity

import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import cn.example.baselib.activity.BaseActivity
import cn.example.myapplication.R
import cn.example.myapplication.fragment.*

class FragmentActivity : BaseActivity(){
    override fun getLayoutId(): Int {
        return R.layout.activity_fragment_main
    }

    override fun initOnCreate() {
        checkPermission()
//        openFragment<CameraTestFragment>(R.id.container, null)
    }

    private fun checkPermission() {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 1)
        } else {
            openFragment<CameraTestFragment>(R.id.container, null)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            1 -> {
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    openFragment<CameraTestFragment>(R.id.container, null)
                }
            }
        }
    }
}