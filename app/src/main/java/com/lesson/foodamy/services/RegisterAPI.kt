package com.lesson.foodamy.services

import com.lesson.foodamy.model.ResponseUser
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RegisterAPI {
    @FormUrlEncoded
    @POST("/api/auth/register")
    suspend fun register(@Field("email") email: String , @Field("password") password:String,@Field("username") username:String) :Response<ResponseUser>
}