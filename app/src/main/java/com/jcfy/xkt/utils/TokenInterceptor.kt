package com.jcfy.xkt.utils

import okhttp3.FormBody
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author linzheng
 */
class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originRequest = chain.request()
        if (!UserUtils.isLogin || UserUtils.user?.token == null)
            return chain.proceed(originRequest)
        val newRequestBuilder = originRequest.newBuilder()
        val newFormBodyBuilder = FormBody.Builder()
        if (originRequest.body() is FormBody) {
            val originFormBody = originRequest.body() as FormBody
            for (index in 0 until originFormBody.size()) {
                newFormBodyBuilder.add(originFormBody.name(index), originFormBody.value(index))
            }
        }
        newFormBodyBuilder.add("token", UserUtils.user?.token)
        newRequestBuilder.method(originRequest.method(), newFormBodyBuilder.build())
        return chain.proceed(newRequestBuilder.build())
    }

}