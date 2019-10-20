package cn.example.myapplication.activity

import android.Manifest
import android.content.ContentValues
import android.content.pm.PackageManager
import android.database.ContentObserver
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.provider.ContactsContract
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import cn.example.myapplication.provider.MyContentProvider
import cn.example.myapplication.provider.MyDatabaseHelper

class UserOperateActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerObserver()
        insertData()
        getDate()
    }

    private fun registerObserver() {
    }

    private fun getDate(){
        val cursor = contentResolver.query(MyContentProvider.USER_CONTENT_URI, null, null, null, null)
        cursor?.let {
            while (cursor.moveToNext()){
                val name = cursor.getString(cursor.getColumnIndex("name"))
                Log.i(TAG, "name : $name ")
            }
        }
        cursor?.close()
    }

    private fun insertData(){
        var contentValues = ContentValues()
        contentValues.put("name", "lee")
        val uri = contentResolver.insert(MyContentProvider.USER_CONTENT_URI, contentValues)
        Log.i(TAG, "insert uri is: $uri")
    }


    companion object{
        const val TAG = "UserOperateActivity"
    }
}