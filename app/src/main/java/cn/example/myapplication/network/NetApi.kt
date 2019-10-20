package cn.example.myapplication.network

import cn.example.myapplication.entity.BaseResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

interface NetApi {
    @POST("login")
    fun login(@Body bodyMap: Map<String, Any?>): Observable<BaseResponse<Nothing>>

    @GET
    fun loadBaidu(@Url url: String): Observable<BaseResponse<String>>
}