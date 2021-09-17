package com.lesson.foodamy

import com.lesson.foodamy.services.AuthAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper private constructor() {
    companion object {
        private var retrofit: Retrofit? = null
        private var authApi: AuthAPI? = null

        private const val BASE_URL = "https://fodamy.mobillium.com/"

         fun invoke() = synchronized(this) {
            retrofit.let {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            retrofit
        }

        fun getAuthAPI() = synchronized(this) {
            authApi.let {
                authApi = retrofit!!.create(AuthAPI::class.java)

            }
            authApi
        }
    }
}