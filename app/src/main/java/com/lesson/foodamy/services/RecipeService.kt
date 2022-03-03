package com.lesson.foodamy.services

import com.lesson.foodamy.model.ResponseComment
import com.lesson.foodamy.model.ResponseLike
import com.lesson.foodamy.model.comment_dataclass.Comment
import com.lesson.foodamy.model.comment_dataclass.ResponseComments
import com.lesson.foodamy.model.recipe_category.ResponseRecipeCategory
import com.lesson.foodamy.model.recipe_dataclass.ResponseRecipes
import com.lesson.foodamy.model.recipe_detail_info.RecipeDetailInfo
import com.lesson.foodamy.model.recipe_detail_info.ResponseFollow
import retrofit2.Response
import retrofit2.http.*

interface RecipeService {

    @GET("api/editor-choices")
    suspend fun getEditorsChoice(@Query("page") page: Int): Response<ResponseRecipes>

    @GET("api/recipe")
    suspend fun getLastAdded(@Query("page") page: Int): Response<ResponseRecipes>

    @GET("api/recipe/{recipe_id}")
    suspend fun getRecipeByID(
        @Path("recipe_id") recipeId:Int,
        @Header("X-Fodamy-Token") token: String
        ) : Response<RecipeDetailInfo>

    @GET("/api/recipe/{recipe_id}/comment")
    suspend fun getComments(@Path("recipe_id") recipeId:Int,@Query("page") page: Int) : Response<ResponseComments>

    @Multipart
    @POST("/api/recipe/{recipe_id}/comment")
    suspend fun addComments(
        @Path("recipe_id") recipeId: Int,
        @Header("X-Fodamy-Token") token: String,
        @Part("text") text: String,
    ): Response<Comment>


    @DELETE("/api/recipe/{recipe_id}/comment/{comment_id}")
    suspend fun deleteComment(
        @Path("recipe_id") recipeId: Int,
        @Path("comment_id") commentId:Int,
        @Header("X-Fodamy-Token") token: String,
    ) : Response<ResponseComment>

    @FormUrlEncoded
    @PUT ("/api/recipe/{recipe_id}/comment/{comment_id}")
    suspend fun editComment(
        @Path("recipe_id") recipeId: Int,
        @Path("comment_id") commentId: Int,
        @Field("text") text: String,
        @Header("X-Fodamy-Token") token: String,

        ) : Response<ResponseComment>

    @POST("/api/recipe/{recipe_id}/like")
    suspend fun likeRecipe(
        @Path("recipe_id") recipeId: Int,
        @Header("X-Fodamy-Token") token: String
    ) : Response<ResponseLike>

    @DELETE("/api/recipe/{recipe_id}/like")
    suspend fun deleteLikeRecipe(
        @Path("recipe_id" ) recipeId: Int,
        @Header("X-Fodamy-Token") token: String
    ) : Response<ResponseLike>

    @POST("/api/user/{followedId}/following")
    suspend fun followUser(
        @Path("followedId") followedId: Int,
        @Header("X-Fodamy-Token") token: String
    ) : Response<ResponseFollow>

    @DELETE("/api/user/{followedId}/following")
    suspend fun deleteFollowUser(
        @Path("followedId") followedId: Int,
        @Header("X-Fodamy-Token") token: String
    ) : Response<ResponseFollow>

    @GET("/api/category-recipes")
    suspend fun getRecipeCategories(@Query("page") page: Int): Response<ResponseRecipeCategory>

    @GET("/api/category/{category_id}/recipe")
    suspend fun getCategoryRecipes(@Path("category_id") categoryID: Int) : Response<ResponseRecipes>
}
