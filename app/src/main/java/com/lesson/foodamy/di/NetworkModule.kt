package com.lesson.foodamy.di

import com.lesson.foodamy.repository.AuthApiRepository
import com.lesson.foodamy.services.AuthService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Mert Gölcü
 * @date 7.02.2022
 */

@Singleton
@Module
object NetworkModule {


    @Provides
    fun provideOkhttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Provides
    @Inject
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("BASE_URL")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideAuthRepository(retrofit: Retrofit): AuthApiRepository {
        return AuthApiRepository(retrofit.create(AuthService::class.java))
    }

}