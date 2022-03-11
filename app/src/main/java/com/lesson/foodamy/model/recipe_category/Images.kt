package com.lesson.foodamy.model.recipe_category

import com.google.gson.annotations.SerializedName

data class Images(

    @SerializedName("width") var width: Int? = null,
    @SerializedName("height") var height: Int? = null,
    @SerializedName("key") var key: String? = null,
    @SerializedName("order") var order: Int? = null,
    @SerializedName("url") var url: String? = null

)
