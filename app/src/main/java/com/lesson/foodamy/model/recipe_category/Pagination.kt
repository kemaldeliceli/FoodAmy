package com.lesson.foodamy.model.recipe_category

import com.google.gson.annotations.SerializedName

data class Pagination(

    @SerializedName("total") var total: Int? = null,
    @SerializedName("per_page") var perPage: Int? = null,
    @SerializedName("current_page") var currentPage: Int? = null,
    @SerializedName("last_page") var lastPage: Int? = null,
    @SerializedName("first_item") var firstItem: Int? = null,
    @SerializedName("last_item") var lastItem: Int? = null,

)
