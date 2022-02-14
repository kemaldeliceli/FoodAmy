package com.lesson.foodamy.model.comment_dataclass

data class User(
    val cover: Any,
    val definition: String,
    val facebook_url: Any,
    val favorites_count: Int,
    val followed_count: Int,
    val following_count: Int,
    val id: Int,
    val image: İmage,
    val instagram_url: Any,
    val is_following: Boolean,
    val is_top_user_choice: Boolean,
    val is_trusted: Int,
    val language: String,
    val likes_count: Int,
    val name: String,
    val recipe_count: Int,
    val surname: String,
    val twitter_url: Any,
    val username: String,
    val youtube_url: Any
)