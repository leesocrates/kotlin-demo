package cn.example.myapplication.activity

import android.os.Bundle
import android.util.LruCache
import androidx.appcompat.app.AppCompatActivity

class ListAcitvity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val lruCache = object : LruCache<String, String>((Runtime.getRuntime().totalMemory() / 1024).toInt()){
            override fun sizeOf(key: String?, value: String?): Int {
                return super.sizeOf(key, value)
            }
        }
    }
}