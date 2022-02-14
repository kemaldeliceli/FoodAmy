package com.lesson.foodamy.di

import androidx.core.content.PermissionChecker
import com.lesson.foodamy.repository.AuthApiRepository
import com.lesson.foodamy.repository.CommentApiRepository
import com.lesson.foodamy.repository.RecipesAPIRepository
import com.lesson.foodamy.services.AuthService
import com.lesson.foodamy.services.CommentService
import com.lesson.foodamy.services.RecipeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    private val BASE_URL = "https://fodamy.mobillium.com/"

    @Provides
    fun providesRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Provides
    @Inject
    fun providesAuthApi(retrofit: Retrofit) : AuthApiRepository {
        return AuthApiRepository(retrofit.create(AuthService::class.java))
    }
    @Provides
    @Inject
    fun providesRecipesApi(retrofit: Retrofit): RecipesAPIRepository{
        return  RecipesAPIRepository(retrofit.create(RecipeService::class.java))
    }
    @Provides
    @Inject
    fun providesCommentsApi(retrofit: Retrofit):CommentApiRepository{
        return CommentApiRepository(retrofit.create(CommentService::class.java))
    }


}