package cn.example.businessone.network

import com.example.lib_network.RetrofitManager

/**
 *  author : Lee
 *  date : 2019/10/29
 *  description :
 */
class BsOneNetService private constructor() {
    @Volatile
    private var mNetApi: BsOneNetApi = RetrofitManager.getInstance().getRetrofit("http://192.168.62.164:8090").create(BsOneNetApi::class.java)

    companion object {
        val instance = BsOneNetServiceHolder.BsOneNetService
    }

    private object BsOneNetServiceHolder {
        val BsOneNetService = BsOneNetService()
    }

    fun getNetApi(): BsOneNetApi {
        return mNetApi
    }
}