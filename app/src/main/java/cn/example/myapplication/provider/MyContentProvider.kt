package cn.example.myapplication.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.ContentObserver
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import android.util.Log
import java.lang.IllegalArgumentException

class MyContentProvider : ContentProvider(){
    val TAG = MyContentProvider::class.java.simpleName
    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        Log.i(TAG, "MyContentProvider insert")
        val table = getTableName(uri)
        table?.let {
            db.insert(table, null, values)
            context?.contentResolver?.notifyChange(uri, null)
            return uri
        }
        throw IllegalArgumentException("Unsupported URI: $uri")
    }

    override fun query(uri: Uri, projection: Array<String>?, selection: String?, selectionArgs: Array<String>?, sortOrder: String?): Cursor? {
        Log.i(TAG, "MyContentProvider query ")
        val table = getTableName(uri)
        table?.let {
            return db.query(table, projection, selection, selectionArgs, null, null, sortOrder, null)
        }
        throw IllegalArgumentException("Unsupported URI: $uri")
    }

    override fun onCreate(): Boolean {
        Log.i(TAG, "MyContentProvider onCreate")
        context?.let {
            mMyDatabaseHelper = MyDatabaseHelper(context!!)
            db = mMyDatabaseHelper.writableDatabase
            return true
        }
        return false
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int {
        val table = getTableName(uri)
        table?.let {
            val row = db.update(table, values, selection, selectionArgs)
            if (row>0){
                context?.contentResolver?.notifyChange(uri, null)
                return row
            }
        }
        throw IllegalArgumentException("Unsupported URI: $uri")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        val table = getTableName(uri)
        table?.let {
            val count = db.delete(table, selection, selectionArgs)
            if (count>0){
                context?.contentResolver?.notifyChange(uri, null)
                return count
            }
        }
        throw IllegalArgumentException("Unsupported URI: $uri")
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    private fun getTableName(uri: Uri): String? {
        return when(sUriMatcher.match(uri)){
            USER_URI_CODE ->{
                MyDatabaseHelper.USER_TABLE_NAME
            }
            else -> {
                null
            }
        }
    }



    companion object{
        const val AUTHORITY = "com.example.socrate.myprovider"
        const val USER_URI_CODE = 0
        const val USER_ITEM = 1
        const val CATEGORY_DIR= 2
        const val CATEGORY_ITEM = 3
        @JvmStatic val USER_CONTENT_URI = Uri.parse("content://$AUTHORITY/user")
        @JvmStatic var sUriMatcher: UriMatcher = UriMatcher(UriMatcher.NO_MATCH)
        private lateinit var mMyDatabaseHelper: MyDatabaseHelper
        private lateinit var db: SQLiteDatabase

        init {
            sUriMatcher.addURI(AUTHORITY,"user",USER_URI_CODE)
        }
    }

}