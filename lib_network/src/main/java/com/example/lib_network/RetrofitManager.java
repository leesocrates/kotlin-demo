package com.example.lib_network;

import com.google.gson.GsonBuilder;

import java.util.HashMap;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * author : Lee
 * date : 2019/10/26
 * description :
 */
public class RetrofitManager {
    private HashMap<String, Retrofit> retrofitMap;

    private RetrofitManager(){
        retrofitMap = new HashMap<>();
    }

    public static RetrofitManager getInstance(){
        return RetrofitManagerHolder.sInstance;
    }
    private static class RetrofitManagerHolder{
        private static final RetrofitManager sInstance = new RetrofitManager();
    }

    public Retrofit getRetrofit(String endpoint){
        Retrofit retrofit = retrofitMap.get(endpoint);
        if(retrofit == null){
            retrofit = generateRetrofit(endpoint);
        }
        return retrofit;
    }

    private synchronized Retrofit generateRetrofit(String endpoint){
        Retrofit retrofit = retrofitMap.get(endpoint);
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(endpoint)
                    .client(OkHttpClientManager.getInstance().getOkHttpClient())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create()))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            retrofitMap.put(endpoint, retrofit);
        }
        return retrofit;
    }

}
