package cn.example.myapplication.fragment

import cn.example.baselib.fragment.BaseFragment
import cn.example.myapplication.R
import kotlinx.android.synthetic.main.fragment_invisible.*

class InVIsibleTestFragment : BaseFragment(){
    override fun getLayoutId(): Int {
        return R.layout.fragment_invisible
    }

    override fun initView() {
       val view = myViewStub.inflate()

    }

}