package com.lesson.foodamy.services

import android.util.Log
import com.lesson.foodamy.model.AuthData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AuthAPIService {
    private val BASE_URL= "https://fodamy.mobillium.com/"

    private lateinit var  api : AuthAPI

    public fun requestAuth(authData: AuthData){
        // Create Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        // Service
        api = retrofit.create(AuthAPI::class.java)


        CoroutineScope(Dispatchers.IO).launch {

            val response = api.getAuth(authData.email,authData.password)

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    println(response.message())
                    Log.d("Pretty Printed JSON :","asdasd")

                } else {

                    Log.e("RETROFIT_ERROR", response.code().toString())

                }
            }

        }
    }



}
