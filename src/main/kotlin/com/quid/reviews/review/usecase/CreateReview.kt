package com.quid.reviews.review.usecase

import com.quid.reviews.image.ImageProcessor
import com.quid.reviews.image.gateway.message.producer.ImageProducer
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
        private val reviewRepository: ReviewRepository, private val imageProducer: ImageProducer
    ) : CreateReview {

        override fun create(request: ReviewCreateRequest): Review =
            request.imgList.map { ImageProcessor.save(it) }
                .let { request.toReview(it) }
                .let { reviewRepository.save(it) }
                .also { imageProducer.compress(it.id!!) }
    }
}