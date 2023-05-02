package com.quid.reviews.review.usecase

import com.quid.reviews.review.domain.Review
import com.quid.reviews.review.gateway.repository.ReviewRepository
import com.quid.reviews.review.gateway.web.ReviewCreateRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface CreateReview {
    fun create(request: ReviewCreateRequest): Review

    @Service
    @Transactional
    class CreateReviewUseCase(
        private val reviewRepository: ReviewRepository
    ) : CreateReview {

        override fun create(request: ReviewCreateRequest): Review =
            request.toReview().let { reviewRepository.save(it) }

    }
}