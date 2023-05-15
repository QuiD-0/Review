package com.quid.reviews.review.gateway.repository.mongoDB

import com.quid.reviews.review.gateway.repository.ReviewDocument
import org.bson.types.ObjectId
import org.springframework.data.repository.CrudRepository

interface MongoReviewRepository : CrudRepository<ReviewDocument, ObjectId> {
    fun findByDeletedFalse(): List<ReviewDocument>
    fun findByIdAndDeletedFalse(id: ObjectId): ReviewDocument?
}