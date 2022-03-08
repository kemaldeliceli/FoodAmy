package com.lesson.foodamy.model.dataclass

import java.lang.Exception

data class BaseException(val code: Int? = null, val error: String? = null): Exception()
