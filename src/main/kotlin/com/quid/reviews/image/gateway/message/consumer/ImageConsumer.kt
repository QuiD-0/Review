package com.quid.reviews.image.gateway.message.consumer

import com.quid.reviews.image.usecase.CompressImage
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component

interface ImageConsumer {
    fun consume(message: String, ack : Acknowledgment)

    @Component
    class KafkaImageConsumer(
        private val compressImage: CompressImage
    ) : ImageConsumer {

        @KafkaListener(topics = ["compressImage"], groupId = "image")
        override fun consume(message: String, ack : Acknowledgment) {
            compressImage.execute(message)
            ack.acknowledge()
        }
    }
}