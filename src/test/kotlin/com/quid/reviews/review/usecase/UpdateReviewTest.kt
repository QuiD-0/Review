package com.quid.reviews.review.usecase

import com.quid.reviews.review.gateway.web.ReviewCreateRequest
import com.quid.reviews.review.gateway.web.ReviewUpdateRequest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Profile

@Profile("test")
@SpringBootTest
class UpdateReviewTest{

    @Autowired
    private lateinit var updateReview: UpdateReview

    @Autowired
    private lateinit var createReview: CreateReview

    private lateinit var id: String

    @BeforeEach
    fun setup(){
        val request = ReviewCreateRequest(
            title = "title",
            description = "description",
            score = 1,
            author = "author",
            productId = 1
        )
        id = createReview.create(request).id!!
    }

    @Test
    fun `리뷰 수정`(){
        val request = ReviewUpdateRequest(
            title = "update title",
            description = "update description",
            score = 5
        )
        val update = updateReview.update(id, request.title, request.description, request.score)
        assertEquals(update.title, request.title)
    }

    @Test
    fun `리뷰 수정 실패`(){
        val request = ReviewUpdateRequest(
            title = "update title",
            description = "update description",
            score = 11
        )
        assertThrows(IllegalArgumentException::class.java){
            updateReview.update(id, request.title, request.description, request.score)
        }

    }
}