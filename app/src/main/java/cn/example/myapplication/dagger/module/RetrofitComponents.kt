package cn.example.myapplication.dagger.module

import cn.example.module_annotation.Modules
import cn.example.myapplication.activity.RetrofitActivity
import dagger.Component
import javax.inject.Singleton

/**
 *  author : Lee
 *  date : 2020/10/17
 *  description :
 */
@Singleton
@Component(modules = [RetrofitServiceModule::class])
open interface RetrofitComponents {
    fun inject(retrofitActivity: RetrofitActivity)
}