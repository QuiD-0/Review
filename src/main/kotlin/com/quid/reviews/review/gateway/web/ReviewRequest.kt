package com.quid.reviews.review.gateway.web

import com.quid.reviews.file.ImageProcessor.Companion.saveImages
import com.quid.reviews.review.domain.Review
import com.quid.reviews.review.domain.createReview
import org.springframework.web.multipart.MultipartFile

data class ReviewCreateRequest(
    val title: String,
    val description: String,
    val score: Int,
    val author: String,
    val productId: Long,
    val imgList: List<MultipartFile> = listOf()
) {
    fun toReview(): Review = createReview(
        title,
        description,
        score,
        productId,
        author,
        saveImages(imgList)
    )
}