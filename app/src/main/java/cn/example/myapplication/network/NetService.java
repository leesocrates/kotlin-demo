package cn.example.myapplication.network;


import com.example.lib_network.RetrofitManager;

/**
 * Created by lee on 2015/11/12.
 *
 */
public class NetService<T> {

    private T mNetApi;

    public NetService(Class<T> tClass) {
        mNetApi = RetrofitManager.getInstance().getRetrofit("http://172.25.157.137:8090").create(tClass);
    }

    public T getNetApi() {
        return mNetApi;
    }


}
