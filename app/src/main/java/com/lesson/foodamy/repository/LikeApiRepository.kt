package com.lesson.foodamy.repository

import com.google.gson.Gson
import com.lesson.foodamy.model.BaseResponse
import com.lesson.foodamy.model.ResponseLike
import com.lesson.foodamy.model.dataclass.ErrorBody
import com.lesson.foodamy.services.LikeService
import java.lang.Exception

class LikeApiRepository(private val likeApiService: LikeService) {

    suspend fun requestLikeRecipe(recipeID: Int, token: String) : BaseResponse<ResponseLike>?{
        var responseLike : BaseResponse<ResponseLike>? = null

        try {
            val response =  likeApiService.likeComments(recipeID,token)

            if (response.isSuccessful){
                responseLike = BaseResponse.Success(response.body()!!)
            }else{
                val errorResponse = response.errorBody()?.string()
                val errorBody = Gson().fromJson<ErrorBody>(errorResponse, ErrorBody::class.java)
                responseLike = BaseResponse.Error(errorBody)
            }

        }catch (e: Exception){
            responseLike = BaseResponse.Error(ErrorBody("408","Timeout Error"))
            println(e.message)
            println(e.toString())
        }

        return  responseLike
    }
}