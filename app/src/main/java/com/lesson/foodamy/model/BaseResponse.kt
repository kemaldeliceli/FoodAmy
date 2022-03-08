package com.lesson.foodamy.model

import com.lesson.foodamy.model.dataclass.BaseException

sealed class BaseResponse<out R> {
    data class Success<out T>(val data: T) : BaseResponse<T>()
    data class Error(val error: BaseException) : BaseResponse<Nothing>()
}
