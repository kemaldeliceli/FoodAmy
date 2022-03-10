package com.lesson.foodamy.di.utils

import com.lesson.foodamy.preferences.IPrefDefaultManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private val sharedPrefManager: IPrefDefaultManager) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        val token = sharedPrefManager.getToken()
        if (token.isNotEmpty())
            builder.addHeader(TOKEN_HEADER, token)
        return chain.proceed(builder.build())
    }

    companion object {
        const val TOKEN_HEADER = "X-Fodamy-Token"
    }
}