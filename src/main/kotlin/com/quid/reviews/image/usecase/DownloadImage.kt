package com.quid.reviews.image.usecase

import com.quid.reviews.image.ImageProcessor
import com.quid.reviews.review.gateway.repository.ReviewRepository
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletResponse


interface DownloadImage {
    fun byReviewId(id: String, response: HttpServletResponse)

    @Service
    class DownloadImageUseCase(
        private val reviewRepository: ReviewRepository
    ) : DownloadImage {
        override fun byReviewId(id: String, response: HttpServletResponse) {
            val review = reviewRepository.findById(id)
            when(review.imgList.size){
                0 -> throw IllegalStateException("No image found for review with id $id")
                1 -> ImageProcessor.download(review.imgList[0], response)
                else -> ImageProcessor.download(review.imgList, response)
            }
        }

    }
}