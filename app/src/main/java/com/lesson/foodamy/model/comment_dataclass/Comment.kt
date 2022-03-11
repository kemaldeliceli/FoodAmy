package com.lesson.foodamy.model.comment_dataclass

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Comment(
    val difference: String? = null,
    val id: Int? = null,
    val text: String? = null,
    val user: @RawValue User
) : Parcelable
