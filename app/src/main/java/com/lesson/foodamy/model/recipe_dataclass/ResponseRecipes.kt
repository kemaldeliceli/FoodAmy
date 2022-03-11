package com.lesson.foodamy.model.recipe_dataclass

data class ResponseRecipes(

    val data: ArrayList<RecipeInfo> = arrayListOf(),
    val pagination: Pagination? = Pagination()

)
