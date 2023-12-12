package com.okproject.moviecollection.data.networking.client

import com.okproject.moviecollection.data.networking.API_TOKEN
import com.okproject.moviecollection.data.networking.JSON_CONTENT_TYPE
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $API_TOKEN")
            .addHeader("accept", JSON_CONTENT_TYPE)
            .build()
        return chain.proceed(request)
    }

}