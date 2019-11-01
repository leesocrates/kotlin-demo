package cn.example.businessone.entity

/**
 * Created by lee on 2017/6/29.
 */
data class BaseResponse<E>(var code: Int, var message: String, var data: E? )