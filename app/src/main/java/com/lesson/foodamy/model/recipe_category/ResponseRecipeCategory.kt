package com.lesson.foodamy.model.recipe_category

import com.google.gson.annotations.SerializedName

data class ResponseRecipeCategory(

    @SerializedName("data") var data: ArrayList<CategoryInfo> = arrayListOf(),
    @SerializedName("pagination") var pagination: Pagination? = Pagination()

)
