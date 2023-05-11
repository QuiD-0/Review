package com.quid.reviews.review.usecase

import com.quid.reviews.review.gateway.web.ReviewCreateRequest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Profile

@Profile("test")
@SpringBootTest
class FindReviewTest {

    @Autowired
    lateinit var createReview: CreateReview
    @Autowired
    lateinit var findReview: FindReview

    @BeforeEach
    fun setup() {
        val request = ReviewCreateRequest(
            title = "title",
            description = "description",
            score = 5,
            author = "author",
            productId = 1
        )
        createReview.create(request)
    }

    @Test
    fun `모든 리뷰 조회`() {
        val reviews = findReview.getReviewList()
        assertTrue { reviews.isNotEmpty() }
    }
}