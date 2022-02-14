package com.lesson.foodamy.model.comment_dataclass

data class ResponseComments(
    val `data`: List<Comment>,
    val pagination: Pagination
)