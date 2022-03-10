package com.lesson.foodamy.repository

import com.google.gson.Gson
import com.lesson.foodamy.model.ErrorBody
import com.lesson.foodamy.model.dataclass.BaseException
import retrofit2.HttpException
import java.lang.Exception

open class BaseRepository {

    suspend fun <T> baseRequest(response: suspend () -> T): T {
        return try {
            response.invoke()
        } catch (e: Exception) {
            throw parseException(e)
        }
    }
    private fun parseException(e: Exception): Exception {
        return when (e) {
            is HttpException -> {
                val errorResponse = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(errorResponse, ErrorBody::class.java)

                BaseException(e.code(), errorBody.error)
            }
            else -> {
                e
            }
        }
    }
}