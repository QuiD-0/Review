package com.quid.reviews.review.gateway.web

data class ReviewCreateRequest(
    val title: String, val description: String, val rating: Int, val userName: String, val productId: Long
)