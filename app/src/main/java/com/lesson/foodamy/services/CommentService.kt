package com.lesson.foodamy.services

import com.lesson.foodamy.model.comment_dataclass.Comment
import com.lesson.foodamy.model.comment_dataclass.ResponseComments
import retrofit2.Response
import retrofit2.http.*

interface CommentService {

    @GET("/api/recipe/{recipe_id}/comment")
    suspend fun getComments(@Path("recipe_id") recipeId:Int) : Response<ResponseComments>

    @Multipart
    @POST("/api/recipe/{recipe_id}/comment")
    suspend fun addComments(
        @Path("recipe_id") recipeId: Int,
        @Header("X-Fodamy-Token") token: String,
        @Part("text") text: String
    ): Response<Comment>


}