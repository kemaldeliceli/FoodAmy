package com.lesson.foodamy.di

import com.lesson.foodamy.RetrofitHelper
import com.lesson.foodamy.repository.AuthAPIRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RetrofitModule {
    @Singleton
    @Provides
    fun providesAuthApi() : AuthAPIRepository {
        return AuthAPIRepository(RetrofitHelper.getAuthAPI()!!)
    }
}