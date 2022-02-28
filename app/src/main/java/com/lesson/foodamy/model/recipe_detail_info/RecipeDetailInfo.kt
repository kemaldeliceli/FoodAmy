package com.lesson.foodamy.model.recipe_detail_info

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue


@Parcelize
data class RecipeDetailInfo (

  @SerializedName("id"                       ) var id                    : Int?              = null,
  @SerializedName("title"                    ) var title                 : String?           = null,
  @SerializedName("definition"               ) var definition            : String?           = null,
  @SerializedName("language"                 ) var language              : String?           = null,
  @SerializedName("ingredients"              ) var ingredients           : String?           = null,
  @SerializedName("directions"               ) var directions            : String?           = null,
  @SerializedName("youtube_url"              ) var youtubeUrl            : String?           = null,
  @SerializedName("youtube_image"            ) var youtubeImage          : String?           = null,
  @SerializedName("view_count"               ) var viewCount             : Int?              = null,
  @SerializedName("is_liked"                 ) var isLiked               : Boolean?          = null,
  @SerializedName("is_favorited"             ) var isFavorited           : Boolean?          = null,
  @SerializedName("difference"               ) var difference            : String?           = null,
  @SerializedName("is_editor_choice"         ) var isEditorChoice        : Boolean?          = null,
  @SerializedName("is_owner"                 ) var isOwner               : Boolean?          = null,
  @SerializedName("like_count"               ) var likeCount             : Int?              = null,
  @SerializedName("number_of_favorite_count" ) var numberOfFavoriteCount : Int?              = null,
  @SerializedName("comment_count"            ) var commentCount          : Int?              = null,
  @SerializedName("is_approved"              ) var isApproved            : Boolean?          = null,
  @SerializedName("user"                     ) var user                  : @RawValue User?             = User(),
  @SerializedName("time_of_recipe"           ) var timeOfRecipe          : @RawValue TimeOfRecipe?     = TimeOfRecipe(),
  @SerializedName("number_of_person"         ) var numberOfPerson        : @RawValue NumberOfPerson?   = NumberOfPerson(),
  @SerializedName("category"                 ) var category              : @RawValue Category?         = Category(),
  @SerializedName("images"                   ) var images                : @RawValue ArrayList<Images> = arrayListOf()

):Parcelable