package com.lesson.foodamy.model.recipe_dataclass

import com.google.gson.annotations.SerializedName


data class User (

  val id              : Int?     = null,
  val username        : String?  = null,
  val name            : String?  = null,
  val surname         : String?  = null,
  val definition      : String?  = null,
  @SerializedName("is_trusted"         ) val isTrusted       : Int?     = null,
  @SerializedName("facebook_url"       ) val facebookUrl     : String?  = null,
  @SerializedName("twitter_url"        ) val twitterUrl      : String?  = null,
  @SerializedName("instagram_url"      ) val instagramUrl    : String?  = null,
  @SerializedName("youtube_url"        ) val youtubeUrl      : String?  = null,
  val language        : String?  = null,
  @SerializedName("is_top_user_choice" ) val isTopUserChoice : Boolean? = null,
  @SerializedName("followed_count"     ) val followedCount   : Int?     = null,
  @SerializedName("following_count"    ) val followingCount  : Int?     = null,
  @SerializedName("recipe_count"       ) val recipeCount     : Int?     = null,
  @SerializedName("is_following"       ) val isFollowing     : Boolean? = null,
  @SerializedName("favorites_count"    ) val favoritesCount  : Int?     = null,
  @SerializedName("likes_count"        ) val likesCount      : Int?     = null,
  val cover           : String?  = null,
  val image           : Image?   = Image()

)