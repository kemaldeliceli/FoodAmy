package com.lesson.foodamy.model.dataclass

import androidx.annotation.StringRes

data class BaseException(val code: Int? = null, val error: String? = null) :
    Exception(message = error)
