package cn.example.myapplication.dagger.module

import android.content.Context
import cn.example.myapplication.network.CommonNetApi
import cn.example.myapplication.network.NetApi
import cn.example.myapplication.network.NetService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 *  author : Lee
 *  date : 2020/10/17
 *  description :
 */
@Module
open class RetrofitServiceModule(val context: Context) {

    @Provides
    fun provideNetApi(): NetApi{
        return NetService<NetApi>(NetApi::class.java).netApi
    }

    @Provides
    @Singleton
    fun provideCommonNetApi(): CommonNetApi{
        return NetService<CommonNetApi>(CommonNetApi::class.java).netApi
    }

    @Provides
    @Singleton
    fun provideContext() = context

}