package com.example.a77299007.myapplication.net

import android.text.TextUtils
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by gongxueyong on 2018/4/23.
 */
class BaseUrlInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()

        val headers = request.headers()
        val urlName = headers.get(InterceptorConst.ARGS_URL_NAME)

        if (!TextUtils.isEmpty(urlName)) {
            val url = request.url().toString()
            val builder = request.newBuilder().url(url.replace(InterceptorConst.RIBBON_API, urlName!!))
                    .removeHeader(InterceptorConst.ARGS_URL_NAME)
            return chain.proceed(builder.build())
        }

        return chain.proceed(request)
    }
}