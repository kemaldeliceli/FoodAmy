package com.lesson.foodamy.services

import android.util.Log
import com.google.gson.Gson
import com.lesson.foodamy.RetrofitHelper
import com.lesson.foodamy.model.AuthData
import com.lesson.foodamy.model.ErrorBody
import com.lesson.foodamy.model.ResponseMessage
import com.lesson.foodamy.model.ResponseUser
import kotlinx.coroutines.*
import java.lang.Exception

object AuthAPIService {
    private lateinit var api: AuthAPI

    private var responseUser: ResponseUser? = null
    private var errorMessage: ErrorBody? = null


    public fun requestAuth(authData: AuthData) : ResponseMessage{
        // Create Retrofit // Service
        RetrofitHelper()
        api = RetrofitHelper.getAuthAPI()!!

        val job = GlobalScope.launch {
            try {
                val response = api.getAuth(authData.email, authData.password)

                if (response.isSuccessful) {
                    responseUser = response.body()
                } else {
                    var errorResponse = response.errorBody()?.string()
                    var errorBody = Gson().fromJson<ErrorBody>(errorResponse, ErrorBody::class.java)
                    errorMessage = errorBody
                }
            } catch (e: Exception) {
                errorMessage = ErrorBody("408","Timeout Error")
            }

        }
        runBlocking {
            job.join()
        }

        return ResponseMessage(responseUser, errorMessage)
    }
}


