package cn.example.myapplication.network

import cn.example.myapplication.entity.BaseResponse
import cn.example.myapplication.entity.UserInfo
import io.reactivex.Observable
import retrofit2.http.*

/**
 *  author : Lee
 *  date : 2020/10/17
 *  description :
 */
interface CommonNetApi {
    @FormUrlEncoded
    @POST("register")
    fun register(@FieldMap bodyMap: Map<String, String>): Observable<BaseResponse<UserInfo>>
}