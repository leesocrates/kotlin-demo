package cn.example.myapplication.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cn.example.myapplication.R
import cn.example.myapplication.network.NetApi
import cn.example.myapplication.network.RetrofitConfig

open class RetrofitActivity : AppCompatActivity(){
    protected val retrofitService: NetApi by lazy { RetrofitConfig.retrofit.create(NetApi::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)
        var map = HashMap<String, Any?>()
        map["name"] = "lee"
        retrofitService.login(map)

    }
}