package com.quid.reviews.review.usecase

import com.quid.reviews.review.gateway.repository.ReviewRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface DeleteReview {
    fun delete(id: String)

    @Service
    @Transactional
    class DeleteReviewUseCase(
        private val reviewRepository: ReviewRepository,
    ) : DeleteReview {
        override fun delete(id: String): Unit = reviewRepository.findById(id).let { reviewRepository.delete(it) }
    }
}