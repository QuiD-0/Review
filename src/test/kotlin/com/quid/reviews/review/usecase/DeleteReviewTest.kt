package com.quid.reviews.review.usecase

import com.quid.reviews.review.gateway.web.ReviewCreateRequest
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Profile

@Profile
@SpringBootTest
class DeleteReviewTest {

    @Autowired
    lateinit var deleteReview: DeleteReview

    @Autowired
    lateinit var createReview: CreateReview

    lateinit var id: String

    @BeforeEach
    fun setup() {
        val request = ReviewCreateRequest(
            title = "title",
            description = "description",
            score = 5,
            author = "author",
            productId = 1
        )
        id = createReview.create(request).id!!
    }

    @Test
    fun `리뷰 삭제`() {
        assertDoesNotThrow { deleteReview.delete(id) }
    }

    @Test
    fun `리뷰 삭제 실패`() {
        assertThrows(Exception::class.java) { deleteReview.delete("1") }
    }
}