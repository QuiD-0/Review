package com.quid.reviews.review.gateway.repository

import com.quid.reviews.review.domain.Review
import com.quid.reviews.review.gateway.repository.mongoDB.MongoReviewRepository
import org.springframework.stereotype.Repository

interface ReviewRepository {
    fun save(review: Review): Review
    fun findAll(): List<Review>
    fun findById(id: String): Review
    fun delete(review: Review)
    fun update(id: String, title: String, description: String, score: Int): Review

    @Repository
    class ReviewRepositoryImpl(
        private val mongoRepository: MongoReviewRepository,
    ) : ReviewRepository {

        override fun save(review: Review) = mongoRepository.save(document(review)).toReview()

        override fun findAll(): List<Review> =
            mongoRepository.findByDeletedFalse().map { it.toReview() }

        override fun findById(id: String): Review =
            mongoRepository.findByIdAndDeletedFalse(id)?.toReview()
                ?: throw Exception("Review not found")

        override fun delete(review: Review): Unit =
            mongoRepository.save(document(review).delete()).let { }

        override fun update(id: String, title: String, description: String, score: Int): Review =
            mongoRepository.findByIdAndDeletedFalse(id)?.let {
                mongoRepository.save(it.update(title, description, score)).toReview()
            } ?: throw Exception("Review not found")

    }
}