package cn.example.businessone.fragment

import cn.example.baselib.fragment.BaseFragment
import cn.example.businessone.network.BsOneNetService

/**
 *  author : Lee
 *  date : 2019/10/29
 *  description :
 */
abstract class BsOneBaseFragment : BaseFragment(){
    val netService = BsOneNetService.instance.getNetApi()
}