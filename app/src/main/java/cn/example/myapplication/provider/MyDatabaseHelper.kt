package cn.example.myapplication.provider

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDatabaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "example.db"
        const val USER_TABLE_NAME = "user"
        private const val CREATE_DATABASE_TABLE = "CREATE TABLE IF NOT EXISTS $USER_TABLE_NAME(id integer primary key autoincrement, name varchar(30))"
    }


    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_DATABASE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

}