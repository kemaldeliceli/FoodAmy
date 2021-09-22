package com.lesson.foodamy.services

import com.google.gson.Gson
import com.lesson.foodamy.RetrofitHelper
import com.lesson.foodamy.model.ErrorBody
import com.lesson.foodamy.model.RegisterData
import com.lesson.foodamy.model.ResponseMessage
import com.lesson.foodamy.model.ResponseUser
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

object RegisterAPIService {
    private lateinit var api: RegisterAPI
    private var responseUser: ResponseUser? = null
    private var errorMessage: ErrorBody? = null

    fun requestRegister(registerData: RegisterData): ResponseMessage {
        // Create Retrofit // Service
        RetrofitHelper.invoke()
        api = RetrofitHelper.getRegisterApi()!!

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