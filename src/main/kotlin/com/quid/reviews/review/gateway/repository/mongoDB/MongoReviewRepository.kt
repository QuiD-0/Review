package com.quid.reviews.review.gateway.repository.mongoDB

import com.quid.reviews.review.gateway.repository.ReviewDocument
import org.springframework.data.repository.CrudRepository

interface MongoReviewRepository : CrudRepository<ReviewDocument, String> {
    fun findByDeletedFalse(): List<ReviewDocument>
}