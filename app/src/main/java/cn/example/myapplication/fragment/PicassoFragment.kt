package cn.example.myapplication.fragment

import cn.example.baselib.fragment.BaseFragment
import cn.example.myapplication.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_picasso.*

class PicassoFragment : BaseFragment(){
    override fun getLayoutId(): Int {
        return R.layout.fragment_picasso
    }

    override fun initView() {
        Picasso.get().load("").into(imgTest)
    }

}