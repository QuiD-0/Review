package com.quid.reviews.review.usecase

import com.quid.reviews.review.gateway.web.ReviewCreateRequest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Profile

@Profile("test")
@SpringBootTest
class CreateReviewTest {

    @Autowired
    lateinit var createReview: CreateReview

    @Test
    fun `리뷰 생성`() {
        val request = ReviewCreateRequest(
            title = "title",
            description = "description",
            score = 5,
            author = "author",
            productId = 1L
        )
        val review = createReview.create(request)
        Assertions.assertEquals(request.title, review.title)
    }

    @Test
    fun `점수가 5 이상일 경우 에러`() {
        val request = ReviewCreateRequest(
            title = "title",
            description = "description",
            score = 11,
            author = "author",
            productId = 1L,
            imgList = listOf()
        )
        Assertions.assertThrows(Exception::class.java) { createReview.create(request) }
    }

}