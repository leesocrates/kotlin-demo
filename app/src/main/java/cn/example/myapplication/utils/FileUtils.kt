package cn.example.myapplication.utils

import android.content.Context
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream

object FileUtils {
    fun getAssetsCacheFile(context: Context, fileName: String): String {
        var cacheFile = File(context.getCacheDir(), fileName)
        try {
            val inputStream = context.getAssets().open(fileName)
            try {
                val outputStream = FileOutputStream (cacheFile)
                try {
                    var buf = ByteArray(1024)
                    var len = inputStream.read(buf)
                    while (len > 0) {
                        outputStream.write(buf, 0, len)
                        len = inputStream.read(buf)
                    }
                } finally {
                    outputStream.close()
                }
            } finally {
                inputStream.close()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return cacheFile.getAbsolutePath()
    }
}