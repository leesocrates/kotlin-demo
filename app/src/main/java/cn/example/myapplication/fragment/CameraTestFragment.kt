package cn.example.myapplication.fragment

import android.hardware.Camera
import android.util.Log
import cn.example.baselib.fragment.BaseFragment
import cn.example.myapplication.R

/**
 *  author : Lee
 *  date : 2019/10/23
 *  description :
 */
class CameraTestFragment : BaseFragment() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_picasso
    }

    override fun initView() {
        var cameraCount = 0
        var cam: Camera? = null

        var cameraInfo = Camera.CameraInfo()
        cameraCount = Camera.getNumberOfCameras() // get cameras number

        for (index in 0 until cameraCount) {
            Camera.getCameraInfo(index, cameraInfo)
            Log.i("Test", "cameraId is $index facing is ${cameraInfo.facing} cameraCount is $cameraCount")
//            if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
//                Log.i("Test", "cameraId is $index facing is ${cameraInfo.facing}")
//            } else {
//
//            }
        }
    }
}