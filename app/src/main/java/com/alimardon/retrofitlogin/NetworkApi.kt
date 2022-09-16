package com.alimardon.retrofitlogin

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface NetworkApi {
    @FormUrlEncoded
    @POST("bot/login")
    suspend fun login(
        @Field("login") login: String,
        @Field("password") password: String
    ): Response<Login>
}