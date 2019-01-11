package com.example.a77299007.myapplication.net;


import android.util.Log;
import com.example.a77299007.myapplication.App;
import com.example.a77299007.myapplication.BuildConfig;
import com.example.a77299007.myapplication.api.CommonApi;
import com.example.a77299007.myapplication.config.Config;
import com.readystatesoftware.chuck.ChuckInterceptor;
import com.wazing.myapplication.converter.DecryptConverter;
import com.wazing.myapplication.converter.DecryptConverterFactory;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/3/14.
 * <p>
 * 网络请求类
 */

public class NetApi {

    private static NetApi mInstance;
    private static CommonApi commonApi;

    public static NetApi getInstance() {
        if (mInstance == null) {
            synchronized (NetApi.class) {
                if (mInstance == null) {
                    mInstance = new NetApi();
                }
            }
        }
        return mInstance;
    }

    private NetApi() {

        Retrofit retrofit = new Retrofit.Builder()
                .client(getOkHttpClient())
                .baseUrl(Config.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                 .addConverterFactory(DecryptConverterFactory.Companion.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        commonApi = retrofit.create(CommonApi.class);

    }

    private OkHttpClient getOkHttpClient() {
        LoggingInterceptor logging = new LoggingInterceptor(msg -> Log.e("http : ", msg));
        logging.setLevel(BuildConfig.DEBUG ? LoggingInterceptor.Level.BODY : LoggingInterceptor.Level.NONE);

        return new OkHttpClient.Builder()
                .connectTimeout(30 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(30 * 1000, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(false) // 失败重发
                .addInterceptor(new BaseUrlInterceptor())
                .addInterceptor(new EncryptionInterceptor())   //加密使用
                .addInterceptor(new ChuckInterceptor(App.getsInstance())) //拦截okhttp数据 就是顶部通知栏抓包用的那个
                .addInterceptor(logging)  //Log日志
                .build();

    }

    public CommonApi getApiService() {
        return commonApi;
    }


}
