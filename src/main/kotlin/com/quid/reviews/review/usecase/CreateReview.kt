package com.quid.reviews.review.usecase

import com.quid.reviews.image.gateway.message.producer.ImageProducer
import com.quid.reviews.image.usecase.SaveImage
import com.quid.reviews.review.domain.Review
import com.quid.reviews.review.gateway.repository.ReviewRepository
import com.quid.reviews.review.gateway.web.ReviewCreateRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@FunctionalInterface
interface CreateReview {
    fun create(request: ReviewCreateRequest): Review

    @Service
    @Transactional
    class CreateReviewUseCase(
        private val saveImage: SaveImage,
        private val reviewRepository: ReviewRepository,
        private val imageProducer: ImageProducer
    ) : CreateReview {

        override fun create(request: ReviewCreateRequest): Review =
            saveImage.list(request.imgList)
                .let { request.toReview(it) }
                .let { reviewRepository.save(it) }
                .also { if(it.imgList.isNotEmpty()) imageProducer.compress(it.id!!) }
    }
}