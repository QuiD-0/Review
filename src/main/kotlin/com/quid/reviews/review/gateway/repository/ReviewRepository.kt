package com.quid.reviews.review.gateway.repository

import com.quid.reviews.review.domain.Review
import com.quid.reviews.review.gateway.repository.mongoDB.MongoReviewRepository
import org.bson.types.ObjectId
import org.springframework.stereotype.Repository

interface ReviewRepository {
    fun save(review: Review): Review
    fun findAll(): List<Review>
    fun findById(id: String): Review
    fun delete(id: String)
    fun update(id: String, title: String, description: String, score: Int): Review

    @Repository
    class ReviewRepositoryImpl(
        private val mongoRepository: MongoReviewRepository,
    ) : ReviewRepository {

        override fun save(review: Review) = mongoRepository.save(document(review)).toReview()

        override fun findAll(): List<Review> =
            mongoRepository.findByDeletedFalse().map { it.toReview() }

        override fun findById(id: String): Review =
            mongoRepository.findByIdAndDeletedFalse(ObjectId(id))?.toReview()
                ?: throw Exception("Review not found")

        override fun delete(id: String): Unit =
            Unit.run {mongoRepository.findByIdAndDeletedFalse(ObjectId(id))
                    ?.let { mongoRepository.save(it.delete()) } }

        override fun update(id: String, title: String, description: String, score: Int): Review =
            mongoRepository.findByIdAndDeletedFalse(ObjectId(id))?.let {
                mongoRepository.save(it.update(title, description, score)).toReview()
            } ?: throw Exception("Review not found")

    }
}