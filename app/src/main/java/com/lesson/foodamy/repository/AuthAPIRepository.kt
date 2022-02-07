package com.lesson.foodamy.repository

import com.google.gson.Gson
import com.lesson.foodamy.model.BaseResponse
import com.lesson.foodamy.model.ResponseUser
import com.lesson.foodamy.model.dataclass.AuthData
import com.lesson.foodamy.model.dataclass.ErrorBody
import com.lesson.foodamy.model.dataclass.RegisterData
import com.lesson.foodamy.services.AuthService

class AuthApiRepository(private val authService: AuthService) {

    suspend fun requestLogin(authData: AuthData): BaseResponse<ResponseUser>? {
        // Create Retrofit // Service
        var responseLogin: BaseResponse<ResponseUser>? = null

        try {
            val response = authService.getAuth(authData.email, authData.password)

            if (response.isSuccessful) {
                responseLogin = BaseResponse.Success(response.body()!!)
                when (responseLogin) {
                    is BaseResponse.Success -> {
                        val token = responseLogin.data.token
                    }
                }
            } else {
                val errorResponse = response.errorBody()?.string()
                val errorBody = Gson().fromJson<ErrorBody>(errorResponse, ErrorBody::class.java)
                responseLogin = BaseResponse.Error(errorBody)
            }
        } catch (e: Exception) {
            responseLogin = BaseResponse.Error(ErrorBody("408", "Timeout Error"))
        }

        return responseLogin
    }

    suspend fun requestRegister(registerData: RegisterData): BaseResponse<ResponseUser>? {
        var responseRegister: BaseResponse<ResponseUser>? = null

        try {
            val response =
                authService.register(
                    registerData.email,
                    registerData.password,
                    registerData.username
                )

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


