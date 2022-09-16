package com.alimardon.retrofitlogin

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl("https://ilkbotim.ga/")
            .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()
    }
}