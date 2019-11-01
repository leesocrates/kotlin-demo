package cn.example.myapplication.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cn.example.myapplication.R
import cn.example.myapplication.network.NetApi
import cn.example.myapplication.network.NetService
import com.example.lib_network.RetrofitManager

open class RetrofitActivity : AppCompatActivity(){
    private val retrofitService: NetApi by lazy { NetService<NetApi>(NetApi::class.java).netApi }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)
        var map = HashMap<String, Any?>()
        map["name"] = "lee"
        retrofitService.login(map)

    }
}