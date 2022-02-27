package com.lesson.foodamy.model.recipe_category

import com.google.gson.annotations.SerializedName


data class NumberOfPerson (

  @SerializedName("id"   ) var id   : Int?    = null,
  @SerializedName("text" ) var text : String? = null

)