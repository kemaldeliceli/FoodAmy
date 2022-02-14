package com.lesson.foodamy.model.comment_dataclass

data class Comment(
    val difference: String,
    val id: Int,
    val text: String,
    val user: User
)