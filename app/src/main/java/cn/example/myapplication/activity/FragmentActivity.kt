package cn.example.myapplication.activity

import cn.example.baselib.activity.BaseActivity
import cn.example.myapplication.R
import cn.example.myapplication.fragment.*

class FragmentActivity : BaseActivity(){
    override fun getLayoutId(): Int {
        return R.layout.activity_fragment_main
    }

    override fun initOnCreate() {
        openFragment<SocketFragment>(R.id.container, null)
    }


}