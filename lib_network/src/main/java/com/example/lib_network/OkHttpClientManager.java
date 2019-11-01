package com.example.lib_network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * author : Lee
 * date : 2019/10/26
 * description :
 */
public class OkHttpClientManager {
    private OkHttpClient okHttpClient;

    public static OkHttpClientManager getInstance(){
        return OkHttpClientManagerHolder.sInstance;
    }

    public OkHttpClient getOkHttpClient(){
        return okHttpClient;
    }

    private OkHttpClientManager(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

    }

    private static class OkHttpClientManagerHolder{
        private static final OkHttpClientManager sInstance = new OkHttpClientManager();
    }

}
