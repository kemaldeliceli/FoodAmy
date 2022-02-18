package com.lesson.foodamy.services

import com.lesson.foodamy.model.ResponseLike
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface LikeService {

    @POST("/api/recipe/{recipe_id}/like")
    suspend fun likeComments(
        @Path("recipe_id") recipeId: Int,
        @Header("X-Fodamy-Token") token: String,
    )
            : Response<ResponseLike>
}