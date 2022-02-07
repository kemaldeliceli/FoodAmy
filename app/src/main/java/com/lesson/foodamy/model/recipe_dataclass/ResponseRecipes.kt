package com.lesson.foodamy.model.recipe_dataclass

import com.google.gson.annotations.SerializedName
import com.lesson.foodamy.model.recipe_dataclass.Data
import com.lesson.foodamy.model.recipe_dataclass.Pagination


data class ResponseRecipes (

  val data       : ArrayList<Data> = arrayListOf(),
  val pagination : Pagination?     = Pagination()

)