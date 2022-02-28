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

class RecipesAPIRepository(private val recipeService: RecipeService) {

    suspend fun requestRecipes(recipeType: RecipeType,page: Int): BaseResponse<ResponseRecipes>? {
        var responseRecipes: BaseResponse<ResponseRecipes>? = null

        try {
            val response = when (recipeType) {
                RecipeType.EDITORS_CHOICE -> recipeService.getEditorsChoice(page)
                RecipeType.RECENTLY_LAST_ADDED -> recipeService.getLastAdded(page)
            }

            if (response.isSuccessful) {
                responseRecipes = BaseResponse.Success(response.body()!!)
            } else {
                val errorResponse = response.errorBody()?.string()
                val errorBody = Gson().fromJson<ErrorBody>(errorResponse, ErrorBody::class.java)
                responseRecipes = BaseResponse.Error(errorBody)
            }

        } catch (e: Exception) {
            responseRecipes = BaseResponse.Error(ErrorBody("408", "Timeout Error"))
        }

        return responseRecipes
    }

    suspend fun requestLikeRecipe(recipeID: Int, token: String): BaseResponse<ResponseLike>? {
        var responseLike: BaseResponse<ResponseLike>? = null

        try {
            val response = recipeService.likeRecipe(recipeID, token)

            if (response.isSuccessful) {
                responseLike = BaseResponse.Success(response.body()!!)
            } else {
                val errorResponse = response.errorBody()?.string()
                val errorBody = Gson().fromJson<ErrorBody>(errorResponse, ErrorBody::class.java)
                responseLike = BaseResponse.Error(errorBody)
            }

        } catch (e: Exception) {
            responseLike = BaseResponse.Error(ErrorBody("408", "Timeout Error"))
        }

        return responseLike
    }
    suspend fun requestDeleteLikeRecipe(recipeID: Int, token: String): BaseResponse<ResponseLike>? {
        var responseLike: BaseResponse<ResponseLike>? = null

        try {
            val response = recipeService.deleteLikeRecipe(recipeID, token)

            if (response.isSuccessful) {
                responseLike = BaseResponse.Success(response.body()!!)
            } else {
                val errorResponse = response.errorBody()?.string()
                val errorBody = Gson().fromJson<ErrorBody>(errorResponse, ErrorBody::class.java)
                responseLike = BaseResponse.Error(errorBody)
            }

        } catch (e: Exception) {
            responseLike = BaseResponse.Error(ErrorBody("408", "Timeout Error"))
        }

        return responseLike
    }
    suspend fun requestFollowUser(followedId: Int, token: String): BaseResponse<ResponseFollow>? {
        var responseFollow: BaseResponse<ResponseFollow>? = null

        try {
            val response = recipeService.followUser(followedId, token)

            if (response.isSuccessful) {
                responseFollow = BaseResponse.Success(response.body()!!)
            } else {
                val errorResponse = response.errorBody()?.string()
                val errorBody = Gson().fromJson<ErrorBody>(errorResponse, ErrorBody::class.java)
                responseFollow = BaseResponse.Error(errorBody)
            }

        } catch (e: Exception) {
            responseFollow = BaseResponse.Error(ErrorBody("408", "Timeout Error"))
        }

        return responseFollow
    }
    suspend fun requestDeleteFollowUser(followedId: Int, token: String): BaseResponse<ResponseFollow>? {
        var responseDeleteFollow: BaseResponse<ResponseFollow>? = null

        responseDeleteFollow = try {
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

        return responseDeleteFollow
    }
    suspend fun requestComments(recipeID: Int): BaseResponse<ResponseComments>? {
        var responseComments: BaseResponse<ResponseComments>? = null

        try {
            val response = recipeService.getComments(recipeID)

            if (response.isSuccessful) {
                responseComments = BaseResponse.Success(response.body()!!)
            } else {
                val errorResponse = response.errorBody()?.string()
                val errorBody = Gson().fromJson<ErrorBody>(errorResponse, ErrorBody::class.java)
                responseComments = BaseResponse.Error(errorBody)
            }

        } catch (e: Exception) {
            responseComments = BaseResponse.Error(ErrorBody("408", "Timeout Error"))
        }

        return responseComments
    }

    suspend fun requestAddComments(
        recipeID: Int,
        token: String,
        text: String,
    ): BaseResponse<Comment>? {
        var responseAddComments: BaseResponse<Comment>? = null

        try {
            val response = recipeService.addComments(recipeID, token, text)
            println("--$text--")
            if (response.isSuccessful) {
                responseAddComments = BaseResponse.Success(response.body()!!)
            } else {
                val errorResponse = response.errorBody()?.string()
                val errorBody = Gson().fromJson<ErrorBody>(errorResponse, ErrorBody::class.java)
                responseAddComments = BaseResponse.Error(errorBody)
            }

        } catch (e: Exception) {
            responseAddComments = BaseResponse.Error(ErrorBody("408", "Timeout Error"))
        }

        return responseAddComments
    }
    suspend fun requestDeleteComment(
        recipeID: Int,
        commentID:Int,
        token: String
    ): BaseResponse<ResponseComment>? {
        var responseDeleteComment: BaseResponse<ResponseComment>? = null

        try {
            val response = recipeService.deleteComment(recipeID,commentID,token)

            if (response.isSuccessful) {
                responseDeleteComment = BaseResponse.Success(response.body()!!)
            } else {
                val errorResponse = response.errorBody()?.string()
                val errorBody = Gson().fromJson<ErrorBody>(errorResponse, ErrorBody::class.java)
                responseDeleteComment = BaseResponse.Error(errorBody)
            }

        } catch (e: Exception) {
            responseDeleteComment = BaseResponse.Error(ErrorBody("408", "Timeout Error"))
        }

        return responseDeleteComment
    }
    suspend fun requestEditComment(
        recipeID: Int,
        commentID:Int,
        text: String,
        token: String
    ): BaseResponse<ResponseComment>? {
        var responseEdit: BaseResponse<ResponseComment>? = null

        try {
            val response = recipeService.editComment(recipeID,commentID,text,token)

            if (response.isSuccessful) {
                responseEdit = BaseResponse.Success(response.body()!!)
            } else {
                val errorResponse = response.errorBody()?.string()
                val errorBody = Gson().fromJson<ErrorBody>(errorResponse, ErrorBody::class.java)
                responseEdit = BaseResponse.Error(errorBody)
            }

        } catch (e: Exception) {
            responseEdit = BaseResponse.Error(ErrorBody("408", "Timeout Error"))
        }

        return responseEdit
    }
    suspend fun requestRecipeCategories(): BaseResponse<ResponseRecipeCategory>? {
        var responseResponseRecipeCategories: BaseResponse<ResponseRecipeCategory>? = null

        try {
            val response = recipeService.getRecipeCategories()

            if (response.isSuccessful) {
                responseResponseRecipeCategories = BaseResponse.Success(response.body()!!)
            } else {
                val errorResponse = response.errorBody()?.string()
                val errorBody = Gson().fromJson<ErrorBody>(errorResponse, ErrorBody::class.java)
                responseResponseRecipeCategories = BaseResponse.Error(errorBody)
            }

        } catch (e: Exception) {
            responseResponseRecipeCategories = BaseResponse.Error(ErrorBody("408", "Timeout Error"))
        }

        return responseResponseRecipeCategories
    }

    suspend fun requestCategoryRecipes(recipeID: Int): BaseResponse<ResponseRecipes>? {
        var responseRecipes: BaseResponse<ResponseRecipes>? = null

        try {
            val response = recipeService.getCategoryRecipes(recipeID)

            if (response.isSuccessful) {
                responseRecipes = BaseResponse.Success(response.body()!!)
            } else {
                val errorResponse = response.errorBody()?.string()
                val errorBody = Gson().fromJson<ErrorBody>(errorResponse, ErrorBody::class.java)
                responseRecipes = BaseResponse.Error(errorBody)
            }

        } catch (e: Exception) {
            responseRecipes = BaseResponse.Error(ErrorBody("408", "Timeout Error"))
        }

        return responseRecipes
    }

    suspend fun requestRecipeByID(
        recipeID: Int,
        token: String,
    ): BaseResponse<RecipeDetailInfo>? {
        var responseRecipeDetail: BaseResponse<RecipeDetailInfo>? = null

        try {
            val response = recipeService.getRecipeByID(recipeID, token)
            if (response.isSuccessful) {
                responseRecipeDetail = BaseResponse.Success(response.body()!!)
            } else {
                val errorResponse = response.errorBody()?.string()
                val errorBody = Gson().fromJson<ErrorBody>(errorResponse, ErrorBody::class.java)
                responseRecipeDetail = BaseResponse.Error(errorBody)
            }

        } catch (e: Exception) {
            responseRecipeDetail = BaseResponse.Error(ErrorBody("408", e.message.toString()))
        }

        return responseRecipeDetail
    }
}