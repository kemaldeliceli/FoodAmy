package com.lesson.foodamy.services

import com.lesson.foodamy.model.recipe_dataclass.ResponseRecipes
import retrofit2.Response
import retrofit2.http.GET

interface RecipeService {

    @GET("api/recipe")
    suspend fun getLastAdded(): Response<ResponseRecipes>

    @GET("api/editor-choices")
    suspend fun getEditorsChoice(): Response<ResponseRecipes>

}