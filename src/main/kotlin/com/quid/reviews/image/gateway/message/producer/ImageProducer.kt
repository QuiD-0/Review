package com.quid.reviews.image.gateway.message.producer

import com.quid.reviews.review.domain.Review
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

interface ImageProducer {
    fun compress(review: Review)

    @Component
    class KafkaImageProducer(
        private val kafkaTemplate: KafkaTemplate<String, String>,
    ) : ImageProducer {

        override fun compress(review: Review) {
            if (review.imageListIsNotEmpty()) {
                kafkaTemplate.send("compressImage", review.id)
            }
        }
    }
}