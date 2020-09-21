package com.zm.jvmtest;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * author : Lee
 * date : 2020/8/20
 * description :
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
                Log.e("ThreadTest", "uncatch : "+e.getMessage());
                if(e.getMessage()!=null && e.getMessage().contains("pthread_create")){
                    printSysInfo("/proc/meminfo");
                    printSysInfo("/proc/"+android.os.Process.myPid()+"/status");
                    Log.e("ThreadTest", "outofmemory");
                }
            }
        });
    }

    private void printSysInfo(String path){
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(path));

            ByteArrayOutputStream result = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fileInputStream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
            Log.e("ThreadTest", path+" out is : "+result.toString("UTF-8")+"\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
