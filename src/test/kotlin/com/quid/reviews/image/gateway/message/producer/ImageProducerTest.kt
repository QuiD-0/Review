package com.quid.reviews.image.gateway.message.producer

import com.quid.reviews.review.domain.createReview
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ImageProducerTest(@Autowired val imageProducer: ImageProducer) {

    @Test
    @Disabled
    fun producerTest() {
        val review = createReview(
            title = "test",
            description = "test",
            rating = 1,
            productId = 1,
            author = "test",
            imgList = listOf()
        )
        imageProducer.compress(review)
    }
}