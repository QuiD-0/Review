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
        ReviewValidator(title, description, rating, imgList).validate()
    }

    fun imageListIsNotEmpty(): Boolean = imgList.isNotEmpty()

    fun getImgSize(): Int = imgList.size

    fun update(
        updatedTitle: String,
        updatedDescription: String,
        updatedScore: Int,
    ): Review {
        return Review(
            id = this.id,
            title = updatedTitle,
            description = updatedDescription,
            rating = updatedScore,
            productId = this.productId,
            author = this.author,
            createdAt = this.createdAt,
            updatedAt = LocalDateTime.now(),
            deleted = this.deleted,
            imgList = this.imgList
        )
    }

    fun delete(): Review {
        return Review(
            id = this.id,
            title = this.title,
            description = this.description,
            rating = this.rating,
            productId = this.productId,
            author = this.author,
            createdAt = this.createdAt,
            updatedAt = LocalDateTime.now(),
            deleted = true,
            imgList = this.imgList
        )
    }
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