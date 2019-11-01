package cn.example.businessone.network

import cn.example.businessone.entity.BaseResponse
import cn.example.businessone.entity.UserInfo
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

/**
 *  author : Lee
 *  date : 2019/10/29
 *  description :
 */
@JvmSuppressWildcards
interface BsOneNetApi {
    @POST("login")
    fun login(@Body bodyMap: Map<String, Any>): Observable<BaseResponse<List<UserInfo>>>

    @POST("register")
    fun register(@Body bodyMap: Map<String, Any>): Observable<BaseResponse<UserInfo>>
}