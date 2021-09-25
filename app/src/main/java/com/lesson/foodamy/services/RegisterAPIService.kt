package com.lesson.foodamy.services

import com.google.gson.Gson
import com.lesson.foodamy.RetrofitHelper
import com.lesson.foodamy.model.*
import com.lesson.foodamy.model.dataclass.ErrorBody
import com.lesson.foodamy.model.dataclass.RegisterData


object RegisterAPIService {
    private lateinit var api: RegisterAPI

    suspend fun requestRegister(registerData: RegisterData): BaseResponse<ResponseUser>? {
        var responseRegister: BaseResponse<ResponseUser>? = null

        // Create Retrofit // Service
        RetrofitHelper.invoke()
        api = RetrofitHelper.getRegisterApi()!!

        try {
            val response =
                api.register(registerData.email, registerData.password, registerData.username)

            if (response.isSuccessful) {
                responseRegister = BaseResponse.Success(response.body()!!)
            } else {
                val errorResponse = response.errorBody()?.string()
                val errorBody = Gson().fromJson(errorResponse, ErrorBody::class.java)
                responseRegister = BaseResponse.Error(errorBody)
            }
        } catch (e: Exception) {
            responseRegister = BaseResponse.Error(ErrorBody("408", "Timeout Error"))
        }


        return responseRegister
    }
}