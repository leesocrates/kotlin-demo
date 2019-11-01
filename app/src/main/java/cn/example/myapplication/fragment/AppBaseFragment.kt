package cn.example.myapplication.fragment

import cn.example.baselib.fragment.BaseFragment
import cn.example.myapplication.network.NetApi
import cn.example.myapplication.network.NetService


/**
 * Created by socrates on 2016/4/3.
 */
abstract class AppBaseFragment : BaseFragment() {
    protected val retrofitService: NetApi by lazy { NetService(NetApi::class.java).netApi }

}