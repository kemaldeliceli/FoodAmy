package com.lesson.foodamy.services

import com.lesson.foodamy.model.comment_dataclass.ResponseComments
import retrofit2.Response
import retrofit2.http.*

interface CommentService {

    @GET("/api/recipe/{recipe_id}/comment")
    suspend fun getComments(@Path("recipe_id") recipeId:Int) : Response<ResponseComments>
}