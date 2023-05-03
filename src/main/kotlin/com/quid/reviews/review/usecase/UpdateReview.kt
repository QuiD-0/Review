package com.quid.reviews.review.usecase

import com.quid.reviews.image.ImageProcessor
import com.quid.reviews.review.gateway.repository.ReviewRepository
import org.springframework.stereotype.Service

interface UpdateReview {
    fun compressImage(reviewId: String)

    @Service
    class UpdateReviewUseCase(
        private val reviewRepository: ReviewRepository
    ) : UpdateReview{
        override fun compressImage(reviewId: String) {
            val review = reviewRepository.findById(reviewId)
            ImageProcessor.compress(review.imgList)
                .let { reviewRepository.save(review.copy(it)) }
        }
    }
}