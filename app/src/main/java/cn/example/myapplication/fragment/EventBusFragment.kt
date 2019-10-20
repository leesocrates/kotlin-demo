package cn.example.myapplication.fragment

import android.util.Log
import cn.example.baselib.fragment.BaseFragment
import cn.example.myapplication.R
import cn.example.myapplication.entity.message.MessageOne
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class EventBusFragment : BaseFragment() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_picasso
    }

    override fun initView() {
        EventBus.getDefault().post(MessageOne("lee", 20))
        EventBus.getDefault().postSticky(MessageOne("stickName", 40))
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.POSTING, priority = 2, sticky = true)
    fun handleMessage(msg: MessageOne) {
        Log.i(TAG, "onEvent msg $msg")
    }
}