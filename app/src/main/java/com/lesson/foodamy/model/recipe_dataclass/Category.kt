package com.lesson.foodamy.model.recipe_dataclass

import com.google.gson.annotations.SerializedName

data class Category(

    val id: Int? = null,
    val name: String? = null,
    val language: String? = null,
    @SerializedName("main_category_id") val mainCategoryId: Int? = null,
    val image: Image? = Image(),

)
