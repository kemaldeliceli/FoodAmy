package com.lesson.foodamy.model.recipe_detail_info

import com.google.gson.annotations.SerializedName


data class User (

  @SerializedName("id"                 ) var id              : Int?     = null,
  @SerializedName("username"           ) var username        : String?  = null,
  @SerializedName("name"               ) var name            : String?  = null,
  @SerializedName("surname"            ) var surname         : String?  = null,
  @SerializedName("definition"         ) var definition      : String?  = null,
  @SerializedName("is_trusted"         ) var isTrusted       : Int?     = null,
  @SerializedName("facebook_url"       ) var facebookUrl     : String?  = null,
  @SerializedName("twitter_url"        ) var twitterUrl      : String?  = null,
  @SerializedName("instagram_url"      ) var instagramUrl    : String?  = null,
  @SerializedName("youtube_url"        ) var youtubeUrl      : String?  = null,
  @SerializedName("language"           ) var language        : String?  = null,
  @SerializedName("is_top_user_choice" ) var isTopUserChoice : Boolean? = null,
  @SerializedName("followed_count"     ) var followedCount   : Int?     = null,
  @SerializedName("following_count"    ) var followingCount  : Int?     = null,
  @SerializedName("recipe_count"       ) var recipeCount     : Int?     = null,
  @SerializedName("is_following"       ) var isFollowing     : Boolean? = null,
  @SerializedName("favorites_count"    ) var favoritesCount  : Int?     = null,
  @SerializedName("likes_count"        ) var likesCount      : Int?     = null,
  @SerializedName("cover"              ) var cover           : String?  = null,
  @SerializedName("image"              ) var image           : Image?  = Image()

)