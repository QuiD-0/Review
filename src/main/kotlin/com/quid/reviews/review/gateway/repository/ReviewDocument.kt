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
    private var title: String,
    private var description: String,
    private var rating: Int,
    @Indexed private val productId: Long,
    @Indexed private val author: String,
    private val createdAt: LocalDateTime,
    private var updatedAt: LocalDateTime,
    private var deleted: Boolean,
    private val imgList: List<String>,
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
        )
    }
}

fun document(review: Review): ReviewDocument {
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
    )
}