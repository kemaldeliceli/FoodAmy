package com.lesson.foodamy.model.recipe_detail_info

import com.google.gson.annotations.SerializedName

data class Category(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("language") var language: String? = null,
    @SerializedName("main_category_id") var mainCategoryId: String? = null,
    @SerializedName("image") var image: Image? = Image(),

)
