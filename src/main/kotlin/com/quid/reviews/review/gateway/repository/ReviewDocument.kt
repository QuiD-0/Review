package com.quid.reviews.review.gateway.repository

import com.quid.reviews.review.domain.Review
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document("reviews")
class ReviewDocument(
    @Id private val id: ObjectId,
    private val title: String,
    private val description: String,
    private val rating: Int,
    @Indexed private val productId: Long,
    @Indexed private val author: String,
    private val createdAt: LocalDateTime,
    private val updatedAt: LocalDateTime,
    private val deleted: Boolean,
    private val imgList: List<String>,
    private val compressedImgList: List<String> = listOf()
) {
    fun toReview(): Review {
        return Review(
            id.toHexString(),
            title,
            description,
            rating,
            productId,
            author,
            createdAt,
            updatedAt,
            deleted,
            imgList,
            compressedImgList
        )
    }
}

fun ofReview(review: Review): ReviewDocument {
    return ReviewDocument(
        review.id?.let { ObjectId(it) } ?: ObjectId.get(),
        review.title,
        review.description,
        review.rating,
        review.productId,
        review.author,
        review.createdAt,
        review.updatedAt,
        review.deleted,
        review.imgList,
        review.compressedImgList)
}