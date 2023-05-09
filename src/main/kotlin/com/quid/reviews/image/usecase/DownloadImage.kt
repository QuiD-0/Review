package com.quid.reviews.image.usecase

import com.quid.reviews.image.ImageProcessor
import com.quid.reviews.review.gateway.repository.ReviewRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.servlet.http.HttpServletResponse

@FunctionalInterface
interface DownloadImage {
    fun byReviewId(id: String, response: HttpServletResponse)

    @Service
    @Transactional(readOnly = true)
    class DownloadImageUseCase(
        private val reviewRepository: ReviewRepository,
    ) : DownloadImage {
        override fun byReviewId(id: String, response: HttpServletResponse) =
            reviewRepository.findById(id).let {
                    when (it.imgList.size) {
                        0 -> throw IllegalStateException("No image found for review with id $id")
                        1 -> ImageProcessor.download(it.imgList[0], response)
                        else -> ImageProcessor.download(it.imgList, response)
                    }
                }
    }
}