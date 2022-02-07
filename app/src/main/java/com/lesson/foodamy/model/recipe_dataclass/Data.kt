package com.lesson.foodamy.model.recipe_dataclass

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


@SuppressLint("ParcelCreator")
data class Data (

  val id                    : Int?              = null,
  val title                 : String?           = null,
  val definition            : String?           = null,
  val language              : String?           = null,
  val ingredients           : String?           = null,
  val directions            : String?           = null,
  @SerializedName("youtube_url"              ) val youtubeUrl            : String?           = null,
  @SerializedName("youtube_image"            ) val youtubeImage          : String?           = null,
  val difference            : String?           = null,
  @SerializedName("is_editor_choice"         ) val isEditorChoice        : Boolean?          = null,
  @SerializedName("is_owner"                 ) val isOwner               : Boolean?          = null,
  @SerializedName("like_count"               ) val likeCount             : Int?              = null,
  @SerializedName("number_of_favorite_count" ) val numberOfFavoriteCount : Int?              = null,
  @SerializedName("comment_count"            ) val commentCount          : Int?              = null,
  @SerializedName("is_approved"              ) val isApproved            : Boolean?          = null,
  val user                  : User?             = User(),
  @SerializedName("time_of_recipe"           ) val timeOfRecipe          : TimeOfRecipe?     = TimeOfRecipe(),
  @SerializedName("number_of_person"         ) val numberOfPerson        : NumberOfPerson?   = NumberOfPerson(),
  val category              : Category?         = Category(),
  val images                : ArrayList<Images> = arrayListOf()

):Parcelable{
  override fun describeContents(): Int {
    return Parcelable.CONTENTS_FILE_DESCRIPTOR
  }

  override fun writeToParcel(dest: Parcel?, flags: Int) {
  }

}