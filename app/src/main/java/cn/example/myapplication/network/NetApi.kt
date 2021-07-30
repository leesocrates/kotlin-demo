package cn.example.myapplication.network

import cn.example.myapplication.entity.BaseResponse
import cn.example.myapplication.entity.UserInfo
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

interface NetApi {

    @POST("login")
    fun login(@Body bodyMap: Map<String, String>): Observable<BaseResponse<List<UserInfo>>>

    @POST("register")
    fun register(@Body bodyMap: Map<String, String>): Observable<BaseResponse<UserInfo>>
}