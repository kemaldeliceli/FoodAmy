package com.lesson.foodamy.model.recipe_dataclass

import com.google.gson.annotations.SerializedName

data class Pagination(

    val total: Int? = null,
    @SerializedName("per_page") val perPage: Int? = null,
    @SerializedName("current_page") val currentPage: Int? = null,
    @SerializedName("last_page") val lastPage: Int? = null,
    @SerializedName("first_item") val firstItem: Int? = null,
    @SerializedName("last_item") val lastItem: Int? = null,

)
