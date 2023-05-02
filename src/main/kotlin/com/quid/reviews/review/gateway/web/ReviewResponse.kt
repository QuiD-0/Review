package com.quid.reviews.review.gateway.web

import com.quid.reviews.review.domain.Review
import java.time.LocalDateTime

data class ReviewResponse(
    val id: String,
    val title: String,
    val author: String,
    val score: Int,
    val createAt: LocalDateTime
) {
    companion object {
        fun of(review: Review): ReviewResponse {
            return ReviewResponse(
                review.id!!, review.title, review.author, review.rating, review.createdAt
            )
        }
    }
}