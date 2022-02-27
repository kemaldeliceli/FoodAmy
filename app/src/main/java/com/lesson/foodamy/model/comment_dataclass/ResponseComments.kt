package com.lesson.foodamy.model.comment_dataclass

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class ResponseComments(
    val data: @RawValue Comments,
    val pagination: @RawValue Pagination
):Parcelable

@Parcelize
class Comments: ArrayList<Comment>(), Parcelable