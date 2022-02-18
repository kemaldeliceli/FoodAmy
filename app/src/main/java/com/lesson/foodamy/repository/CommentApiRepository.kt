package com.lesson.foodamy.repository

import com.google.gson.Gson
import com.lesson.foodamy.model.BaseResponse
import com.lesson.foodamy.model.comment_dataclass.Comment
import com.lesson.foodamy.model.comment_dataclass.ResponseComments
import com.lesson.foodamy.model.dataclass.ErrorBody
import com.lesson.foodamy.services.CommentService
import java.lang.Exception

class CommentApiRepository (private val commentService: CommentService) {

    suspend fun requestComments(recipeID: Int):  BaseResponse<ResponseComments>?
    {
        var responseComments : BaseResponse<ResponseComments>? = null

        try {
            val response =  commentService.getComments(recipeID)

            if (response.isSuccessful){
                responseComments = BaseResponse.Success(response.body()!!)
            }else{
                val errorResponse = response.errorBody()?.string()
                val errorBody = Gson().fromJson<ErrorBody>(errorResponse, ErrorBody::class.java)
                responseComments = BaseResponse.Error(errorBody)
            }

        }catch (e: Exception){
            responseComments = BaseResponse.Error(ErrorBody("408","Timeout Error"))
            println(e.message)
            println(e.toString())
        }

        return  responseComments
    }
    suspend fun requestAddComments(recipeID: Int , token:String, text: String):  BaseResponse<Comment>?
    {
        var responseAddComments : BaseResponse<Comment>? = null

        try {
            val response =  commentService.addComments(recipeID, token,text)

            if (response.isSuccessful){
                responseAddComments = BaseResponse.Success(response.body()!!)
            }else{
                val errorResponse = response.errorBody()?.string()
                val errorBody = Gson().fromJson<ErrorBody>(errorResponse, ErrorBody::class.java)
                responseAddComments = BaseResponse.Error(errorBody)
            }

        }catch (e: Exception){
            responseAddComments = BaseResponse.Error(ErrorBody("408","Timeout Error"))
            println(e.message)
            println(e.toString())
        }

        return  responseAddComments
    }



}