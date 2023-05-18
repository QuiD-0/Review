package com.quid.reviews.review.usecase

import com.quid.reviews.review.domain.Review
import com.quid.reviews.review.gateway.repository.ReviewRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface UpdateReview {
    fun update(id: String, title: String, description: String, score: Int): Review

    @Service
    @Transactional
    class UpdateReviewUseCase(
        private val reviewRepository: ReviewRepository,
    ) : UpdateReview {
        override fun update(id: String, title: String, description: String, score: Int): Review =
            reviewRepository.findById(id).let {
                reviewRepository.save(it.update(title, description, score))
            }
    }
}