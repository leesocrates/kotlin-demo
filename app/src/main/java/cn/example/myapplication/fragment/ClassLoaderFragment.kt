package cn.example.myapplication.fragment

import android.util.Log
import cn.example.baselib.fragment.BaseFragment
import cn.example.myapplication.R



class ClassLoaderFragment : BaseFragment(){
    override fun getLayoutId(): Int {
        return R.layout.fragment_classloader
    }

    override fun initView() {
        var classLoader = context?.classLoader

        if (classLoader != null) {
            Log.i(TAG, " classLoader " + classLoader!!.toString())
            while (classLoader!!.getParent() != null) {
                classLoader = classLoader!!.getParent()
                Log.i(TAG, " classLoader " + classLoader!!.toString())
            }
        }
    }

}