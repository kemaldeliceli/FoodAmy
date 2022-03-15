package com.lesson.foodamy.model.recipe_category

import com.google.gson.annotations.SerializedName

data class CategoryInfo(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("language") var language: String? = null,
    @SerializedName("main_category_id") var mainCategoryId: String? = null,
    @SerializedName("recipe_count") var recipeCount: Int? = null,
    @SerializedName("recipes") var recipes: ArrayList<Recipes> = arrayListOf(),
    @SerializedName("image") var image: Image? = Image(),

)
