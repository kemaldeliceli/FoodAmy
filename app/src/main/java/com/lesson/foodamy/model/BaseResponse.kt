package com.lesson.foodamy.model

import com.lesson.foodamy.model.dataclass.ErrorBody

sealed class BaseResponse<out R>{
    data class Success<out T>(val data: T) : BaseResponse<T>()
    data class Error(val error: ErrorBody) : BaseResponse<Nothing>()
}