package com.lesson.foodamy.repository

import com.lesson.foodamy.model.recipe_dataclass.ResponseRecipes
import com.google.gson.Gson
import com.lesson.foodamy.model.*
import com.lesson.foodamy.model.comment_dataclass.Comment
import com.lesson.foodamy.model.comment_dataclass.ResponseComments
import com.lesson.foodamy.model.dataclass.ErrorBody
import com.lesson.foodamy.model.recipe_category.ResponseRecipeCategory
import com.lesson.foodamy.model.recipe_detail_info.RecipeDetailInfo
import com.lesson.foodamy.model.recipe_detail_info.ResponseFollow
import com.lesson.foodamy.services.RecipeService
import java.lang.Exception

class RecipesAPIRepository(
    private val recipeService: RecipeService, private val token: String
) {


    suspend fun requestLikeRecipe(recipeID: Int): BaseResponse<ResponseLike>? {
         return  try {
            val response = recipeService.likeRecipe(recipeID,this.token)

            if (response.isSuccessful) {
                BaseResponse.Success(response.body()!!)
            } else {
                val errorResponse = response.errorBody()?.string()
                val errorBody = Gson().fromJson<ErrorBody>(errorResponse, ErrorBody::class.java)
                BaseResponse.Error(errorBody)
            }

        } catch (e: Exception) {
            BaseResponse.Error(ErrorBody("408", "Timeout Error"))
        }
    }
    suspend fun requestDeleteLikeRecipe(recipeID: Int): BaseResponse<ResponseLike>? {
        return try {
            val response = recipeService.deleteLikeRecipe(recipeID, token)

            if (response.isSuccessful) {
                BaseResponse.Success(response.body()!!)
            } else {
                val errorResponse = response.errorBody()?.string()
                val errorBody = Gson().fromJson<ErrorBody>(errorResponse, ErrorBody::class.java)
                BaseResponse.Error(errorBody)
            }

        } catch (e: Exception) {
            BaseResponse.Error(ErrorBody("408", "Timeout Error"))
        }
    }
    suspend fun requestFollowUser(followedId: Int): BaseResponse<ResponseFollow>? {

        return try {
            val response = recipeService.followUser(followedId, token)

            if (response.isSuccessful) {
                BaseResponse.Success(response.body()!!)
            } else {
                val errorResponse = response.errorBody()?.string()
                val errorBody = Gson().fromJson<ErrorBody>(errorResponse, ErrorBody::class.java)
                BaseResponse.Error(errorBody)
            }

        } catch (e: Exception) {
            BaseResponse.Error(ErrorBody("408", "Timeout Error"))
        }
    }
    suspend fun requestDeleteFollowUser(followedId: Int): BaseResponse<ResponseFollow>? {

        return try {
            val response = recipeService.deleteFollowUser(followedId, token)

            if (response.isSuccessful) {
                BaseResponse.Success(response.body()!!)
            } else {
                val errorResponse = response.errorBody()?.string()
                val errorBody = Gson().fromJson<ErrorBody>(errorResponse, ErrorBody::class.java)
                BaseResponse.Error(errorBody)
            }

        } catch (e: Exception) {
            BaseResponse.Error(ErrorBody("408", "Timeout Error"))
        }

    }
    suspend fun requestComments(recipeID: Int): BaseResponse<ResponseComments>? {

        return try {
            val response = recipeService.getComments(recipeID,1)

            if (response.isSuccessful) {
                BaseResponse.Success(response.body()!!)
            } else {
                val errorResponse = response.errorBody()?.string()
                val errorBody = Gson().fromJson<ErrorBody>(errorResponse, ErrorBody::class.java)
                BaseResponse.Error(errorBody)
            }

        } catch (e: Exception) {
            BaseResponse.Error(ErrorBody("408", "Timeout Error"))
        }
    }

    suspend fun requestAddComments(
        recipeID: Int,
        text: String,
    ): BaseResponse<Comment>? {
        return try {
            val response = recipeService.addComments(recipeID, token, text)

            if (response.isSuccessful) {
                BaseResponse.Success(response.body()!!)
            } else {
                val errorResponse = response.errorBody()?.string()
                val errorBody = Gson().fromJson<ErrorBody>(errorResponse, ErrorBody::class.java)
                BaseResponse.Error(errorBody)
            }

        } catch (e: Exception) {
            BaseResponse.Error(ErrorBody("408", "Timeout Error"))
        }
    }
    suspend fun requestDeleteComment(
        recipeID: Int,
        commentID:Int,
    ): BaseResponse<ResponseComment>? {

        return try {
            val response = recipeService.deleteComment(recipeID,commentID,token)

            if (response.isSuccessful) {
                BaseResponse.Success(response.body()!!)
            } else {
                val errorResponse = response.errorBody()?.string()
                val errorBody = Gson().fromJson<ErrorBody>(errorResponse, ErrorBody::class.java)
                BaseResponse.Error(errorBody)
            }

        } catch (e: Exception) {
            BaseResponse.Error(ErrorBody("408", "Timeout Error"))
        }
    }
    suspend fun requestEditComment(
        recipeID: Int,
        commentID:Int,
        text: String,
    ): BaseResponse<ResponseComment>? {

        return  try {
            val response = recipeService.editComment(recipeID,commentID,text,token)

            if (response.isSuccessful) {
                BaseResponse.Success(response.body()!!)
            } else {
                val errorResponse = response.errorBody()?.string()
                val errorBody = Gson().fromJson<ErrorBody>(errorResponse, ErrorBody::class.java)
                BaseResponse.Error(errorBody)
            }

        } catch (e: Exception) {
            BaseResponse.Error(ErrorBody("408", "Timeout Error"))
        }
    }
    suspend fun requestRecipeCategories(): BaseResponse<ResponseRecipeCategory>? {

        return try {
            val response = recipeService.getRecipeCategories(0)

            if (response.isSuccessful) {
                BaseResponse.Success(response.body()!!)
            } else {
                val errorResponse = response.errorBody()?.string()
                val errorBody = Gson().fromJson<ErrorBody>(errorResponse, ErrorBody::class.java)
                BaseResponse.Error(errorBody)
            }

        } catch (e: Exception) {
            BaseResponse.Error(ErrorBody("408", "Timeout Error"))
        }
    }

    suspend fun requestRecipeByID(
        recipeID: Int,
    ): BaseResponse<RecipeDetailInfo>? {

        return try {
            val response = recipeService.getRecipeByID(recipeID, token)
            if (response.isSuccessful) {
                BaseResponse.Success(response.body()!!)
            } else {
                val errorResponse = response.errorBody()?.string()
                val errorBody = Gson().fromJson<ErrorBody>(errorResponse, ErrorBody::class.java)
                BaseResponse.Error(errorBody)
            }

        } catch (e: Exception) {
            BaseResponse.Error(ErrorBody("408", e.message.toString()))
        }
    }
}