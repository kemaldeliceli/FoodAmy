package com.lesson.foodamy.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserInfo(
    val id: Int?,
    val email: String?,
    val username: String?,
    val name: String?,
    val surname: String?,
    val definition: String?,
    val phone: String?,
    val tckn: String?,
    val gender: String?,
    val birthday: String?,
    @SerializedName("is_banned") val isBanned: Int?,
    @SerializedName("is_trusted") val isTrusted: Int?,
    @SerializedName("facebook_url") val facebookUrl: String?,
    @SerializedName("twitter_url") val twitterUrl: String?,
    @SerializedName("instagram_url") val instagramUrl: String?,
    @SerializedName("youtube_url") val youtubeUrl: String?,
    val language: String?,
    @SerializedName("is_top_user_choice") val isTopUserChoice: Boolean?,
    @SerializedName("followed_count") val followedCount: Int?,
    @SerializedName("following_count") val followingCount: Int?,
    @SerializedName("recipe_count") val recipeCount: Int?,
    @SerializedName("is_following") val isFollowing: Boolean?,
    @SerializedName("favorites_count") val favoritesCount: Int?,
    @SerializedName("likes_count") val likesCount: Int?,
    val cover: String?,
    val image: String?,
    @SerializedName("top_user") val topUser: String?,
    @SerializedName("cover_image") val coverImage: String?,
) : Parcelable

@Parcelize
data class UserInformation(
    val id: Int?,
    val email: String?,
    val username: String?,
    val name: String?,
    val surname: String?,
    val followedCount: Int?,
    val followingCount: Int?,
    val recipeCount: Int?,
    val isFollowing: Boolean?,
    val favoritesCount: Int?,
    val likesCount: Int?,
    val cover: String?,
    val image: String?,
    val coverImage: String?,
) : Parcelable

fun UserInfo.toResult(): UserInformation {
    return UserInformation(
        this.id,
        this.email,
        this.username,
        this.name,
        this.surname,
        this.followedCount,
        this.followingCount,
        this.recipeCount,
        this.isFollowing,
        this.favoritesCount,
        this.likesCount,
        this.cover,
        this.image,
        this.coverImage
    )
}
