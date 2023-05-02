package com.quid.reviews.review.gateway.web

import com.quid.reviews.review.domain.Review
import java.time.LocalDateTime

data class ReviewResponse(
    val id: String,
    val title: String,
    val author: String,
    val createAd: LocalDateTime
) {
    companion object {
        fun of(review: Review): ReviewResponse {
            return ReviewResponse(
                id = review.id!!,
                title = review.title,
                author = review.author,
                createAd = review.createdAt
            )
        }
    }
}