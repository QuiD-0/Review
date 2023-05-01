package com.quid.reviews.review.gateway.repository

import com.quid.reviews.review.domain.Review
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document("reviews")
class ReviewDocument(
    @Id private val id: ObjectId,
    private val title: String,
    private val description: String,
    private val rating: Int,
    private val productId: Long,
    private val userId: Long,
    private val createdAt: LocalDateTime,
    private val updatedAt: LocalDateTime,
    private val deleted: Boolean,
    private val imgList: List<String>,
) {
    fun toReview(): Review {
        return Review(
            id = id.toHexString(),
            title = title,
            description = description,
            rating = rating,
            productId = productId,
            userId = userId,
            createdAt = createdAt,
            updatedAt = updatedAt,
            deleted = deleted,
            imgList = imgList,
        )
    }
}

fun ofReview(review: Review): ReviewDocument {
    return ReviewDocument(
        id = review.id?.let { ObjectId(it) } ?: ObjectId.get(),
        title = review.title,
        description = review.description,
        rating = review.rating,
        productId = review.productId,
        userId = review.userId,
        createdAt = review.createdAt,
        updatedAt = review.updatedAt,
        deleted = review.deleted,
        imgList = review.imgList,
    )
}