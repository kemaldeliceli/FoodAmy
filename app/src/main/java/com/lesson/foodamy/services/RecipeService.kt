package com.lesson.foodamy.services

import com.lesson.foodamy.model.ResponseLike
import com.lesson.foodamy.model.comment_dataclass.Comment
import com.lesson.foodamy.model.comment_dataclass.ResponseComments
import com.lesson.foodamy.model.recipe_category.ResponseRecipeCategory
import com.lesson.foodamy.model.recipe_dataclass.RecipeInfo
import com.lesson.foodamy.model.recipe_dataclass.ResponseRecipes
import retrofit2.Response
import retrofit2.http.*

interface RecipeService {

    @GET("api/editor-choices")
    suspend fun getEditorsChoice(): Response<ResponseRecipes>

    @GET("api/recipe")
    suspend fun getLastAdded(): Response<ResponseRecipes>

    @GET("/api/recipe/{recipe_id}/comment")
    suspend fun getComments(@Path("recipe_id") recipeId:Int) : Response<ResponseComments>

    @Multipart
    @POST("/api/recipe/{recipe_id}/comment")
    suspend fun addComments(
        @Path("recipe_id") recipeId: Int,
        @Header("X-Fodamy-Token") token: String,
        @Part("text") text: String
    ): Response<Comment>

    @POST("/api/recipe/{recipe_id}/like")
    suspend fun likeComments(
        @Path("recipe_id") recipeId: Int,
        @Header("X-Fodamy-Token") token: String,
    )
            : Response<ResponseLike>

    @GET("/api/category-recipes")
    suspend fun getRecipeCategories(): Response<ResponseRecipeCategory>

    @GET("/api/category/{category_id}/recipe")
    suspend fun getCategoryRecipes(@Path("category_id") recipeID: Int) : Response<ResponseRecipes>
}