package com.lesson.foodamy.di

import com.lesson.foodamy.RetrofitHelper
import com.lesson.foodamy.repository.AuthApiRepository
import com.lesson.foodamy.repository.RecipesAPIRepository
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
    fun providesAuthApi() : AuthApiRepository {
        return AuthApiRepository(RetrofitHelper.getAuthAPI()!!)
    }
    @Singleton
    @Provides
    fun providesRecipesApi(): RecipesAPIRepository{
        return  RecipesAPIRepository(RetrofitHelper.getRecipesAPI()!!)
    }
}