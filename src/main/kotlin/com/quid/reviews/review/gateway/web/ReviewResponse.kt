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

data class ReviewDetailResponse(
    val id: String,
    val title: String,
    val description: String,
    val author: String,
    val score: Int,
    val createAt: LocalDateTime,
    val imgList: List<String>
) {
    companion object {
        fun of(review: Review): ReviewDetailResponse {
            return ReviewDetailResponse(
                review.id!!,
                review.title,
                review.description,
                review.author,
                review.rating,
                review.createdAt,
                review.compressedImgList
            )
        }
    }
}