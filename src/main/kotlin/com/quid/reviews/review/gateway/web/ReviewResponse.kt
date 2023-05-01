package com.quid.reviews.review.gateway.web

import com.quid.reviews.review.domain.Review

data class ReviewResponse(
    val title: String,
    val description: String,
    val rating: Int,
) {
    companion object {
        fun of(create: Review): ReviewResponse {
            return ReviewResponse(
                title = create.title,
                description = create.description,
                rating = create.rating,
            )
        }
    }
}