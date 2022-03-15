package com.lesson.foodamy.model.recipe_category

import com.google.gson.annotations.SerializedName

data class Recipes(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("definition") var definition: String? = null,
    @SerializedName("language") var language: String? = null,
    @SerializedName("ingredients") var ingredients: String? = null,
    @SerializedName("directions") var directions: String? = null,
    @SerializedName("youtube_url") var youtubeUrl: String? = null,
    @SerializedName("youtube_image") var youtubeImage: String? = null,
    @SerializedName("difference") var difference: String? = null,
    @SerializedName("is_editor_choice") var isEditorChoice: Boolean? = null,
    @SerializedName("is_owner") var isOwner: Boolean? = null,
    @SerializedName("like_count") var likeCount: Int? = null,
    @SerializedName("number_of_favorite_count") var numberOfFavoriteCount: Int? = null,
    @SerializedName("comment_count") var commentCount: Int? = null,
    @SerializedName("is_approved") var isApproved: Boolean? = null,
    @SerializedName("user") var user: User? = User(),
    @SerializedName("time_of_recipe") var timeOfRecipe: TimeOfRecipe? = TimeOfRecipe(),
    @SerializedName("number_of_person") var numberOfPerson: NumberOfPerson? = NumberOfPerson(),
    @SerializedName("category") var category: Category? = Category(),
    @SerializedName("images") var images: ArrayList<Images> = arrayListOf(),

)
