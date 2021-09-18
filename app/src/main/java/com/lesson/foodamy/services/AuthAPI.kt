package com.lesson.foodamy.services

import com.lesson.foodamy.model.UserInfo
import com.lesson.foodamy.model.AuthData
import com.lesson.foodamy.model.ResponseUser
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthAPI {
    @FormUrlEncoded
    @POST("api/auth/login")
    suspend fun getAuth(@Field("username") email:String, @Field("password") password:String) :Response<ResponseUser>
}