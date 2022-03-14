package com.lesson.foodamy.model.exception

data class BaseException(val code: Int? = null, val error: String? = null) : Exception()
