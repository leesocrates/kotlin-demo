package cn.example.myapplication.activity

import android.text.TextUtils
import android.util.Log
import cn.example.baselib.activity.BaseActivity
import cn.example.myapplication.R
import cn.example.myapplication.dagger.module.DaggerRetrofitComponents
import cn.example.myapplication.dagger.module.RetrofitServiceModule
import cn.example.myapplication.network.CommonNetApi
import cn.example.myapplication.network.NetApi
import com.lee.library.util.toastLong
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_retrofit.*
import javax.inject.Inject

open class RetrofitActivity : BaseActivity(){
    @Inject
    lateinit var retrofitService: NetApi
    @Inject
    lateinit var commonRetrofitService: CommonNetApi

    override fun getLayoutId(): Int {
        return R.layout.activity_retrofit
    }

    override fun initOnCreate() {
        DaggerRetrofitComponents.builder().retrofitServiceModule(RetrofitServiceModule(this)).build().inject(this)
        initView()
    }

    fun initView() {
        loginBtn.setOnClickListener {
            login()
        }
        registerBtn.setOnClickListener {
            register()
        }

    }

    private fun login() {
        if (TextUtils.isEmpty(usernameEt.text.toString())) {
            toastLong("username could not be null")
            return
        } else if (TextUtils.isEmpty(passwordEt.text.toString())) {
            toastLong("password could not be null")
            return
        }
        val map = mutableMapOf<String, String>()
        map["username"] = usernameEt.text.toString()
        map["password"] = passwordEt.text.toString()

        retrofitService.login(map)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    it.data?.let {
                        it->
                        Log.i(TAG, it.toString())
                    }
                }, {
                    it.printStackTrace()
                })
    }

    private fun register() {
        if (TextUtils.isEmpty(usernameEt.text.toString())) {
            toastLong("username could not be null")
            return
        } else if (TextUtils.isEmpty(passwordEt.text.toString())) {
            toastLong("password could not be null")
            return
        }
        val map = mutableMapOf<String, String>()
        map["username"] = usernameEt.text.toString()
        map["password"] = passwordEt.text.toString()
        map["test"] = "Lenovo TB-X605M"

        commonRetrofitService.register(map)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    it.data?.let {
                        it->
                        Log.i(TAG, it.toString())
                    }
                }, {
                    it.printStackTrace()
                })
    }

}