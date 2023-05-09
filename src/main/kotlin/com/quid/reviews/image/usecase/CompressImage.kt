package com.quid.reviews.image.usecase

import com.quid.reviews.image.ImageProcessor
import com.quid.reviews.review.gateway.repository.ReviewRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@FunctionalInterface
interface CompressImage {

    fun execute(id: String)

    @Service
    @Transactional(readOnly = true)
    class CompressImageUseCase(
        private val reviewRepository: ReviewRepository,
    ) : CompressImage {

        override fun execute(id: String) =
            reviewRepository.findById(id).imgList.forEach { ImageProcessor.compress(it) }
    }
}