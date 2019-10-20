package com.lee.library.util

import android.view.View

fun String?.notNullOrEmpty(f:()->Unit){
    if(this!=null && this.isNotEmpty()){
        f()
    }
}

fun View.dp2Px(value: Float): Float{
    val scale = context.resources.displayMetrics.density
    return value * scale + 0.5f
}

fun View.px2Dp(value: Float): Float{
    val scale = context.resources.displayMetrics.density
    return value / scale + 0.5f
}


