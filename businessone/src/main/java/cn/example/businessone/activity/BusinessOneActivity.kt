package cn.example.businessone.activity

import cn.example.baselib.activity.BaseActivity
import cn.example.businessone.R
import cn.example.businessone.fragment.LoginFragment

/**
 *  author : Lee
 *  date : 2019/10/29
 *  description :
 */
class BusinessOneActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_bsone
    }

    override fun initOnCreate() {
        openFragment<LoginFragment>(R.id.fragmentContainer)
    }

}