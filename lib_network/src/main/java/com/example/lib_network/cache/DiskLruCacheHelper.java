package com.example.lib_network.cache;

import android.util.Log;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.File;
import java.io.IOException;

/**
 * author : Lee
 * date : 2019/10/30
 * description :
 */
public class DiskLruCacheHelper {
    private  String TAG = DiskLruCacheHelper.this.getClass().getSimpleName();
    DiskLruCache mDiskLruCache;

    public void init(File directory, int appVersion, int valueCount, long maxSize){
        try {
            mDiskLruCache = DiskLruCache.open(directory, appVersion, valueCount, maxSize);
        } catch (IOException e) {
            Log.e(TAG, "DiskLruCacheHelper init failed IOException");
        }
    }


}
