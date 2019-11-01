package com.lee.library.util

import android.content.Context
import android.view.View
import android.widget.Toast

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

fun Context.toastLong(msg: String){
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}

fun Context.toastShort(msg: String){
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}


