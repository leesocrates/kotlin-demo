package cn.example.businessone.fragment

import android.text.TextUtils
import android.util.Log
import cn.example.baselib.fragment.BaseFragment
import cn.example.businessone.R
import cn.example.businessone.network.BsOneNetService
import com.lee.library.util.toastLong
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_bsone.*
import kotlinx.android.synthetic.main.fragment_login.*

/**
 *  author : Lee
 *  date : 2019/10/29
 *  description :
 */
class LoginFragment: BsOneBaseFragment() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_login
    }

    override fun initView() {
        loginBtn.setOnClickListener {
            login()
        }
        registerBtn.setOnClickListener {
            register()
        }

    }

    private fun login() {
        if (TextUtils.isEmpty(usernameEt.text.toString())) {
            context?.toastLong("username could not be null")
            return
        } else if (TextUtils.isEmpty(passwordEt.text.toString())) {
            context?.toastLong("password could not be null")
            return
        }
        val map = mutableMapOf<String, String>()
        map["username"] = usernameEt.text.toString()
        map["password"] = passwordEt.text.toString()

        BsOneNetService.instance.getNetApi().login(map)
                .subscribeOn(Schedulers.io())
                .subscribe { it ->
                    it.data?.let {
                        Log.i(TAG, it.toString())
                    }
                }
    }

    private fun register() {
        if (TextUtils.isEmpty(usernameEt.text.toString())) {
            context?.toastLong("username could not be null")
            return
        } else if (TextUtils.isEmpty(passwordEt.text.toString())) {
            context?.toastLong("password could not be null")
            return
        }
        val map = mutableMapOf<String, String>()
        map["username"] = usernameEt.text.toString()
        map["password"] = passwordEt.text.toString()

        BsOneNetService.instance.getNetApi().register(map)
                .subscribeOn(Schedulers.io())
                .subscribe { it ->
                    it.data?.let {
                        Log.i(TAG, it.toString())
                    }
                }
    }

}