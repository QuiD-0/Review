package com.quid.reviews.review.gateway.repository

import com.quid.reviews.review.domain.Review
import com.quid.reviews.review.gateway.repository.mongoDB.MongoReviewRepository
import org.springframework.stereotype.Repository

interface ReviewRepository {
    fun save(review: Review): Review
    fun findAll() : List<Review>

    @Repository
    class ReviewRepositoryImpl(
        private val mongoRepository: MongoReviewRepository
    ) : ReviewRepository {

        override fun save(review: Review) = mongoRepository.save(ofReview(review)).toReview()
        override fun findAll(): List<Review> {
            return mongoRepository.findAll().map { it.toReview() }
        }
    }
}