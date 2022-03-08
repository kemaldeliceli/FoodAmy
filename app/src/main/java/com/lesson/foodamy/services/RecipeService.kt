package com.lesson.foodamy.services

import com.lesson.foodamy.model.ResponseComment
import com.lesson.foodamy.model.ResponseLike
import com.lesson.foodamy.model.comment_dataclass.Comment
import com.lesson.foodamy.model.comment_dataclass.ResponseComments
import com.lesson.foodamy.model.recipe_category.ResponseRecipeCategory
import com.lesson.foodamy.model.recipe_dataclass.ResponseRecipes
import com.lesson.foodamy.model.recipe_detail_info.RecipeDetailInfo
import com.lesson.foodamy.model.recipe_detail_info.ResponseFollow
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeService {

    @GET("api/editor-choices")
    suspend fun getEditorsChoice(@Query("page") page: Int): ResponseRecipes

    @GET("api/recipe")
    suspend fun getLastAdded(@Query("page") page: Int): ResponseRecipes

    @GET("api/recipe/{recipe_id}")
    suspend fun getRecipeByID(
        @Path("recipe_id") recipeId: Int,
       
    ): RecipeDetailInfo

    @GET("/api/recipe/{recipe_id}/comment")
    suspend fun getComments(@Path("recipe_id") recipeId: Int, @Query("page") page: Int): ResponseComments

    @Multipart
    @POST("/api/recipe/{recipe_id}/comment")
    suspend fun addComments(
        @Path("recipe_id") recipeId: Int,
        
        @Part("text") text: String,
    ): Comment

    @DELETE("/api/recipe/{recipe_id}/comment/{comment_id}")
    suspend fun deleteComment(
        @Path("recipe_id") recipeId: Int,
        @Path("comment_id") commentId: Int,
        
    ): ResponseComment

    @FormUrlEncoded
    @PUT("/api/recipe/{recipe_id}/comment/{comment_id}")
    suspend fun editComment(
        @Path("recipe_id") recipeId: Int,
        @Path("comment_id") commentId: Int,
        @Field("text") text: String,
        

    ): ResponseComment

    @POST("/api/recipe/{recipe_id}/like")
    suspend fun likeRecipe(
        @Path("recipe_id") recipeId: Int,
       
    ): ResponseLike

    @DELETE("/api/recipe/{recipe_id}/like")
    suspend fun deleteLikeRecipe(
        @Path("recipe_id") recipeId: Int,
       
    ): ResponseLike

    @POST("/api/user/{followedId}/following")
    suspend fun followUser(
        @Path("followedId") followedId: Int,
       
    ): ResponseFollow

    @DELETE("/api/user/{followedId}/following")
    suspend fun deleteFollowUser(
        @Path("followedId") followedId: Int,
       
    ): ResponseFollow

    @GET("/api/category-recipes")
    suspend fun getRecipeCategories(@Query("page") page: Int): ResponseRecipeCategory

    @GET("/api/category/{category_id}/recipe")
    suspend fun getCategoryRecipes(@Path("category_id") categoryID: Int): ResponseRecipes
}
