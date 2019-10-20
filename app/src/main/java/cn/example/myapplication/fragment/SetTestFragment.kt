package cn.example.myapplication.fragment

import android.os.Build
import android.util.ArrayMap
import android.util.SparseArray
import android.util.SparseBooleanArray
import android.util.SparseIntArray
import androidx.annotation.RequiresApi
import cn.example.baselib.fragment.BaseFragment
import cn.example.myapplication.R

class SetTestFragment: BaseFragment() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_picasso
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun initView() {
        var s = SparseArray<String>()
        var ba = SparseBooleanArray()
        val ia = SparseIntArray()
        s.put(2, "2")
        ba.put(2, true)
        ia.put(3, 3)

        val arrayMap = ArrayMap<String, String>()
    }
}