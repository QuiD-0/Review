package com.quid.reviews.image.gateway.message.consumer

import com.quid.reviews.review.usecase.UpdateReview
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component

interface ImageConsumer {
    fun consume(message: String, ack : Acknowledgment)

    @Component
    class KafkaImageConsumer(
        private val updateReview : UpdateReview
    ) : ImageConsumer {

        @KafkaListener(topics = ["compressImage"], groupId = "image")
        override fun consume(message: String, ack : Acknowledgment) {
            updateReview.compressImage(message)
            ack.acknowledge()
        }
    }
}