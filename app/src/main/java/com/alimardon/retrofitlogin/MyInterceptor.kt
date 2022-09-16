package com.alimardon.retrofitlogin

import android.content.SharedPreferences
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class MyInterceptor(val preferences: SharedPreferences) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer " + preferences.getString("token","")).build()
        return chain.proceed(request)
    }
}