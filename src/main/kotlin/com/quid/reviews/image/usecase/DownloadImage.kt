package com.quid.reviews.image.usecase

import com.quid.reviews.review.gateway.repository.ReviewRepository
import org.springframework.stereotype.Service


interface DownloadImage {

    @Service
    class DownloadImageUseCase(
        private val reviewRepository: ReviewRepository
    ) : DownloadImage {


    }
}