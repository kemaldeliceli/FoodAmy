package com.lesson.foodamy.di

import com.lesson.foodamy.di.utils.AuthInterceptor
import com.lesson.foodamy.preferences.IPrefDefaultManager
import com.lesson.foodamy.repository.AuthAPIRepository
import com.lesson.foodamy.repository.RecipesAPIRepository
import com.lesson.foodamy.services.AuthService
import com.lesson.foodamy.services.RecipeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    private val BASE_URL = "https://fodamy.mobillium.com/"

    @Provides
    fun providesOkHTTP(authInterceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(authInterceptor).build()
    }

    @Provides
    @Inject
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Provides
    @Inject
    fun providesAuthApi(retrofit: Retrofit,prefManager:IPrefDefaultManager) : AuthAPIRepository {
        return AuthAPIRepository(retrofit.create(AuthService::class.java), prefManager)
    }

    @Provides
    @Inject
    fun providesRecipeService(retrofit: Retrofit) : RecipeService{
        return retrofit.create(RecipeService::class.java)
    }

    @Provides
    @Inject
    fun providesRecipesApi(retrofit: Retrofit): RecipesAPIRepository{
        return  RecipesAPIRepository(retrofit.create(RecipeService::class.java))
    }



}