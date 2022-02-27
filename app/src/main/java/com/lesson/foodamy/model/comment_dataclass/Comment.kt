package com.lesson.foodamy.model.comment_dataclass

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Comment(
    val difference: String,
    val id: Int,
    val text: String,
    val user: @RawValue User
):Parcelable