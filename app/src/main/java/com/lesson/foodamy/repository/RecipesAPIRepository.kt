package com.lesson.foodamy.repository

import com.lesson.foodamy.model.recipe_dataclass.ResponseRecipes
import com.google.gson.Gson
import com.lesson.foodamy.model.BaseResponse
import com.lesson.foodamy.model.RecipeType
import com.lesson.foodamy.model.ResponseLike
import com.lesson.foodamy.model.comment_dataclass.Comment
import com.lesson.foodamy.model.comment_dataclass.ResponseComments
import com.lesson.foodamy.model.dataclass.ErrorBody
import com.lesson.foodamy.model.recipe_category.ResponseRecipeCategory
import com.lesson.foodamy.services.RecipeService
import java.lang.Exception

class RecipesAPIRepository(private val recipeService: RecipeService) {

    suspend fun requestRecipes(recipeType: RecipeType): BaseResponse<ResponseRecipes>? {
        var responseRecipes: BaseResponse<ResponseRecipes>? = null

        try {
            val response = when (recipeType) {
                RecipeType.EDITORS_CHOICE -> recipeService.getEditorsChoice()
                RecipeType.RECENTLY_LAST_ADDED -> recipeService.getLastAdded()
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
            val response = recipeService.likeComments(recipeID, token)

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
}