package com.lesson.foodamy.services

import com.google.gson.Gson
import com.lesson.foodamy.RetrofitHelper
import com.lesson.foodamy.model.*
import com.lesson.foodamy.model.dataclass.AuthData
import com.lesson.foodamy.model.dataclass.ErrorBody
import kotlinx.coroutines.*
import java.lang.Exception

object AuthAPIService {
    private lateinit var api: AuthAPI


    suspend fun requestAuth(authData: AuthData): BaseResponse<ResponseUser>? {
        // Create Retrofit // Service
        var responseLogin: BaseResponse<ResponseUser>? = null
        RetrofitHelper.invoke()
        api = RetrofitHelper.getAuthAPI()!!

        try {
            val response = api.getAuth(authData.email, authData.password)

            if (response.isSuccessful) {
                responseLogin = BaseResponse.Success(response.body()!!)
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
}


