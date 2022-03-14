package com.lesson.foodamy.model.recipe_dataclass

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class RecipeInfo(

    val id: Int? = null,
    val title: String? = null,
    val definition: String? = null,
    val language: String? = null,
    val ingredients: String? = null,
    val directions: String? = null,
    @SerializedName("youtube_url") val youtubeUrl: String? = null,
    @SerializedName("youtube_image") val youtubeImage: String? = null,
    val difference: String? = null,
    @SerializedName("is_editor_choice") val isEditorChoice: Boolean? = null,
    @SerializedName("is_owner") val isOwner: Boolean? = null,
    @SerializedName("like_count") val likeCount: Int? = null,
    @SerializedName("number_of_favorite_count") val numberOfFavoriteCount: Int? = null,
    @SerializedName("comment_count") val commentCount: Int? = null,
    @SerializedName("is_approved") val isApproved: Boolean? = null,
    val user: @RawValue User? = User(),
    @SerializedName("time_of_recipe") val timeOfRecipe: @RawValue TimeOfRecipe? = TimeOfRecipe(),
    @SerializedName("number_of_person") val numberOfPerson: @RawValue NumberOfPerson? = NumberOfPerson(),
    val category: @RawValue Category? = Category(),
    val images: @RawValue ArrayList<Image> = arrayListOf(),

) : Parcelable

@Parcelize
data class Recipe(
    val id: Int?,
    val title: String?,
    val category: String?,
    val image: @RawValue Image,
    val user: @RawValue User?,
    val isEditorChoice: Boolean?,
    val commentCount: Int?,
    val likeCount: Int?,

) : Parcelable

fun RecipeInfo.toResult(): Recipe {
    return Recipe(
        this.id,
        this.title,
        this.category?.name,
        this.images[0],
        this.user,
        this.isEditorChoice,
        this.commentCount,
        this.likeCount
    )
}
