package com.quid.reviews.review.usecase

import com.quid.reviews.review.domain.Review
import com.quid.reviews.review.gateway.repository.ReviewRepository
import org.springframework.stereotype.Service

interface FindReview {
    fun getReviewList(): List<Review>

    @Service
    class FindReviewUseCase(
        private val reviewRepository: ReviewRepository
    ) : FindReview {
        override fun getReviewList(): List<Review> = reviewRepository.findAll()
    }
}