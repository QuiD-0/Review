package com.quid.reviews.review.usecase

import org.springframework.stereotype.Service

interface UpdateReview {
    fun compressImage(message: String)

    @Service
    class UpdateReviewUseCase : UpdateReview{
        override fun compressImage(message: String) {
            println("compress image $message")
        }
    }
}