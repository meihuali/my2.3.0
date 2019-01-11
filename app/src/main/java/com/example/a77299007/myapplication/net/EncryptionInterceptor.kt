package com.example.a77299007.myapplication.net

/**
 * Created by Administrator on 2017/3/14.
 */


import com.example.a77299007.myapplication.utils.AppCache

import java.io.IOException

import okhttp3.Interceptor
import okhttp3.Response

/**
 * 对表单数据加密的请求操作
 */
class EncryptionInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val mToken = AppCache.getLoginResult()?.token

        val builder = chain.request()
                .newBuilder()
                .header("Connection", "close")

        if (mToken != null) {
            builder.addHeader("Token", mToken)
           // builder.addHeader("Token", " 12324534") 假Token
        }else{
           // LoginActivity.show() 跳转到登陆界面
        }
        //  builder.addHeader("versionKey", BuildConfig.VERSION_NAME);
        val original = builder.build()



        return chain.proceed(original)
    }
}

