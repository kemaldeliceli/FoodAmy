package com.lesson.foodamy.services

import com.google.gson.Gson
import com.lesson.foodamy.model.ErrorBody
import com.lesson.foodamy.model.RegisterData
import com.lesson.foodamy.model.ResponseMessage
import com.lesson.foodamy.model.ResponseUser
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RegisterAPIService {
    private val BASE_URL = "https://fodamy.mobillium.com/"
    private lateinit var api: RegisterAPI
    private var responseUser: ResponseUser? = null
    private var errorMessage: ErrorBody? = null

    public fun requestRegister(registerData: RegisterData): ResponseMessage {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(RegisterAPI::class.java)

        val job: Job = GlobalScope.launch {

            try {
                val response =
                    api.register(registerData.email, registerData.password, registerData.username)

                if (response.isSuccessful) {
                    responseUser = response.body()
                } else {
                    val errorResponse = response.errorBody()?.string()
                    errorMessage =
                        Gson().fromJson<ErrorBody>(errorResponse, ErrorBody::class.java)

                }
            } catch (e: Exception) {
                errorMessage = ErrorBody("408", "Timeout Error")
            }

        }
        runBlocking {
            job.join()
        }

        return ResponseMessage(responseUser, errorMessage)

    }
}