package com.lesson.foodamy.services

import com.lesson.foodamy.model.ResponseLogout
import com.lesson.foodamy.model.ResponseUser
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthService {
    @FormUrlEncoded
    @POST("api/auth/login")
    suspend fun getAuth(
        @Field("username") email: String,
        @Field("password") password: String,
    ): ResponseUser

    @FormUrlEncoded
    @POST("/api/auth/register")
    suspend fun register(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("username") username: String,
    ): ResponseUser

    @POST("api/auth/logout")
    suspend fun logOut(): ResponseLogout

    @FormUrlEncoded
    @POST("api/auth/login")
    suspend fun login(
        @Field("username") email: String,
        @Field("password") password: String,
    ): ResponseUser
}
