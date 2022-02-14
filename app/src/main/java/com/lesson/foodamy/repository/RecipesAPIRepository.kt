package com.lesson.foodamy.repository

import com.lesson.foodamy.model.recipe_dataclass.ResponseRecipes
import com.google.gson.Gson
import com.lesson.foodamy.model.BaseResponse
import com.lesson.foodamy.model.RecipeType
import com.lesson.foodamy.model.dataclass.ErrorBody
import com.lesson.foodamy.services.RecipeService
import retrofit2.Response
import java.lang.Exception

class RecipesAPIRepository(private val recipeService: RecipeService) {

    suspend fun requestRecipes(recipeType: RecipeType):  BaseResponse<ResponseRecipes>?
    {
        var responseRecipes : BaseResponse<ResponseRecipes>? = null

        try {
            val response = when(recipeType){
                RecipeType.EDITORS_CHOICE -> recipeService.getEditorsChoice()
                RecipeType.RECENTLY_LAST_ADDED -> recipeService.getLastAdded()
            }

            responseRecipes = if (response.isSuccessful){
                BaseResponse.Success(response.body()!!)
            }else{
                val errorResponse = response.errorBody()?.string()
                val errorBody = Gson().fromJson<ErrorBody>(errorResponse, ErrorBody::class.java)
                BaseResponse.Error(errorBody)
            }

        }catch (e:Exception){
            responseRecipes = BaseResponse.Error(ErrorBody("408","Timeout Error"))
            println(e.message)
            println(e.toString())
        }

        return  responseRecipes
    }


}