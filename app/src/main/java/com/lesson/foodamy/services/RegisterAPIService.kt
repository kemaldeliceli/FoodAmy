package com.lesson.foodamy.services

import com.google.gson.Gson
import com.lesson.foodamy.model.ErrorBody
import com.lesson.foodamy.model.RegisterData
import com.lesson.foodamy.model.ResponseMessage
import com.lesson.foodamy.model.ResponseUser
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RegisterAPIService {
    private val BASE_URL = "https://fodamy.mobillium.com/"
    private lateinit var api: RegisterAPI
    private var responseUser: ResponseUser? = null
    private var errorMessage: String? = null

    public fun requestRegister(registerData: RegisterData): ResponseMessage {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        api = retrofit.create(RegisterAPI::class.java)

        val job:Job =   GlobalScope.launch {

                try {
                    val response =
                        api.register(registerData.email, registerData.password, registerData.username)

                    if (response.isSuccessful) {
                        responseUser = response.body()
                    } else {
                        val errorResponse = response.errorBody()?.string()
                        val errorBody =
                            Gson().fromJson<ErrorBody>(errorResponse, ErrorBody::class.java)
                        errorMessage = errorBody.error
                    }
                }catch (e: Exception){
                    errorMessage = "Timeout Error"
                }

        }
        runBlocking {
            job.join()
        }

        return ResponseMessage(responseUser, errorMessage)

    }
}