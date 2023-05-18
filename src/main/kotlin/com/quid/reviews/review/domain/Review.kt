package com.quid.reviews.review.domain

import java.time.LocalDateTime

class Review(
    val id: String? = null,
    val title: String,
    val description: String,
    val rating: Int,
    val productId: Long,
    val author: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = createdAt,
    val deleted: Boolean = false,
    val imgList: List<String> = listOf(),
) {
    init {
        if (rating < 0 || rating > 10) throw IllegalArgumentException("Rating must be between 0 and 5")
        if (title.length > 100) throw IllegalArgumentException("Title must be less than 100 characters")
        if (description.length > 1000) throw IllegalArgumentException("Description must be less than 1000 characters")
        if (imgList.size > 5) throw IllegalArgumentException("Image list must be less than 5")
    }

    fun imageListIsNotEmpty(): Boolean = imgList.isNotEmpty()

    fun getImgSize(): Int = imgList.size

}

fun createReview(
    title: String,
    description: String,
    rating: Int,
    productId: Long,
    author: String,
    imgList: List<String> = listOf()
): Review {
    return Review(
        title = title,
        description = description,
        rating = rating,
        productId = productId,
        author = author,
        imgList = imgList
    )
}