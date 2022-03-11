package com.lesson.foodamy.model.comment_dataclass

data class Pagination(
    val current_page: Int,
    val first_item: Int,
    val last_item: Int,
    val last_page: Int,
    val per_page: Int,
    val total: Int
)
