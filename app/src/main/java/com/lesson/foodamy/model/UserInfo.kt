package com.lesson.foodamy.model

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@SuppressLint("ParcelCreator")
data class UserInfo (
    @SerializedName("id") var id : Int?,
    @SerializedName("email") var email : String?,
    @SerializedName("username") var username : String?,
    @SerializedName("name") var name : String?,
    @SerializedName("surname") var surname : String?,
    @SerializedName("definition") var definition : String?,
    @SerializedName("phone") var phone : String?,
    @SerializedName("tckn") var tckn : String?,
    @SerializedName("gender") var gender : String?,
    @SerializedName("birthday") var birthday : String?,
    @SerializedName("is_banned") var isBanned : Int?,
    @SerializedName("is_trusted") var isTrusted : Int?,
    @SerializedName("facebook_url") var facebookUrl : String?,
    @SerializedName("twitter_url") var twitterUrl : String?,
    @SerializedName("instagram_url") var instagramUrl : String?,
    @SerializedName("youtube_url") var youtubeUrl : String?,
    @SerializedName("language") var language : String?,
    @SerializedName("is_top_user_choice") var isTopUserChoice : Boolean?,
    @SerializedName("followed_count") var followedCount : Int?,
    @SerializedName("following_count") var followingCount : Int?,
    @SerializedName("recipe_count") var recipeCount : Int?,
    @SerializedName("is_following") var isFollowing : Boolean?,
    @SerializedName("favorites_count") var favoritesCount : Int?,
    @SerializedName("likes_count") var likesCount : Int?,
    @SerializedName("cover") var cover : String?,
    @SerializedName("image") var image : String?,
    @SerializedName("top_user") var topUser : String?,
    @SerializedName("cover_image") var coverImage : String?
) :Parcelable {
    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        TODO("Not yet implemented")
    }
}